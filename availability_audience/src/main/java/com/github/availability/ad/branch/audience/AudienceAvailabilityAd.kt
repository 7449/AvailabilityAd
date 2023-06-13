package com.github.availability.ad.branch.audience

import android.content.Context
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.audience.request.AdManager
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdCache
import com.github.availability.ad.debug.AdDebug

object AudienceAvailabilityAd : AvailabilityAd {

    override fun init(context: Context, testIds: List<String>) {
        initialize(context, testIds)
    }

    override fun load(context: Context, config: AdConfig, callback: Ad.AdCallback) {
        create(config).request(context, callback)
    }

    override fun loadOrCache(context: Context, config: AdConfig, callback: Ad.AdCallback) {
        val cache = AdCache.getCache(config.key)
        if (cache != null) {
            callback.callback(cache)
        } else {
            load(context, config, callback)
        }
    }

    override fun create(config: AdConfig): Ad {
        return AdManager.create(config)
    }

    private fun initialize(context: Context, testIds: List<String>) {
        if (!AudienceNetworkAds.isInitialized(context)) {
            if (AdDebug.debug) {
                AdSettings.turnOnSDKDebugger(context)
                AdSettings.addTestDevices(testIds)
            } else {
                AdSettings.setDataProcessingOptions(arrayOf("LDU"), 1, 1000)
            }
            AdSettings.setMixedAudience(false)
            AudienceNetworkAds
                .buildInitSettings(context)
                .withInitListener { }
                .initialize()
        }
    }

}