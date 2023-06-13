package com.github.availability.ad

import android.content.Context
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdCache

interface AvailabilityAd {

    companion object {
        fun AvailabilityAd.request(ctx: Context, config: AdConfig, callback: (ad: Ad) -> Unit) {
            request(ctx, config, object : Ad.AdCallback {
                override fun callback(ad: Ad) {
                    callback.invoke(ad)
                }
            })
        }

        fun Ad.orCache(): Ad? {
            return if (config.key.isNotEmpty()) AdCache.getCacheAndRemove(config.key) else this
        }
    }

    fun init(context: Context, testIds: List<String> = arrayListOf())

    fun request(context: Context, config: AdConfig, callback: Ad.AdCallback)

    fun requestOrCache(context: Context, config: AdConfig, callback: Ad.AdCallback)

    fun create(config: AdConfig): Ad

}