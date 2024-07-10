package com.github.availability.ad.branch.max

import android.content.Context
import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.max.request.AdManager
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.cacheKey
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdCache

object MaxAvailabilityAd : AvailabilityAd {

    override fun init(context: Context) {
//        throw UnsupportedOperationException(
//            "init() is not supported in MaxAvailabilityAd. " +
//                    "@See https://developers.applovin.com/en/android/overview/new-sdk-initialization-api"
//        )
    }

    override fun load(context: Context, config: AdConfig, callback: Ad.Callback) {
        create(config).load(context, callback)
    }

    override fun loadOrCache(context: Context, config: AdConfig, callback: Ad.Callback) {
        val cache = AdCache.getCacheAndRemove(config.cacheKey())
        if (cache == null) {
            load(context, config, callback)
        } else {
            callback.callback(cache)
        }
    }

    override fun create(config: AdConfig): Ad {
        return AdManager.create(config)
    }

}