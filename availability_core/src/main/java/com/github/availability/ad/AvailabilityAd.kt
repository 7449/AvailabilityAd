package com.github.availability.ad

import android.content.Context
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.cacheKey
import com.github.availability.ad.config.cacheKeyNotEmpty
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdCache

interface AvailabilityAd {

    companion object {
        fun AvailabilityAd.load(ctx: Context, config: AdConfig, callback: (ad: Ad) -> Unit) {
            load(ctx, config, object : Ad.Callback {
                override fun callback(ad: Ad) {
                    callback.invoke(ad)
                }
            })
        }

        fun Ad.orCache(): Ad? {
            return if (config.cacheKeyNotEmpty()) AdCache.getCacheAndRemove(config.cacheKey()) else this
        }
    }

    fun init(context: Context)

    fun load(context: Context, config: AdConfig, callback: Ad.Callback)

    fun loadOrCache(context: Context, config: AdConfig, callback: Ad.Callback)

    fun create(config: AdConfig): Ad

}