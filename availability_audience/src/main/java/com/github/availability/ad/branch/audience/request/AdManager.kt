package com.github.availability.ad.branch.audience.request

import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdType
import com.github.availability.ad.debug.AdLog

internal object AdManager {

    fun create(config: AdConfig): Ad {
        AdLog.i("Ad Create Config [$config]")
        return when (config.type) {
            AdType.NATIVE -> AudienceNativeAd(config)
            AdType.NATIVE_BANNER -> AudienceBannerAd(config)
            AdType.INTERSTITIAL, AdType.APP_OPEN -> AudienceInterstitialAd(config)
            AdType.REWARDED_VIDEO -> AudienceRewardedVideoAd(config)
            AdType.REWARDED_INTERSTITIAL -> AudienceRewardedInterstitialAd(config)
        }
    }

}