package com.github.availability.ad.branch.max.request

import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdType
import com.github.availability.ad.debug.AdLog

internal object AdManager {

    fun create(config: AdConfig): Ad {
        AdLog.i("Ad Create Config [$config]")
        return when (config.type) {
            AdType.INTERSTITIAL -> MaxInterstitialAd(config)
            AdType.NATIVE -> MaxNativeAd(config)
            AdType.APP_OPEN -> MaxAppOpenAd(config)
            AdType.NATIVE_BANNER -> MaxBannerAd(config)
            AdType.REWARDED_VIDEO -> MaxRewardedAd(config)
            AdType.REWARDED_INTERSTITIAL -> MaxRewardedAd(config)
        }
    }

}