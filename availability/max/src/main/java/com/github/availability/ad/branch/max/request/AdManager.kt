package com.github.availability.ad.branch.max.request

import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdLog
import com.github.availability.ad.core.AdType

internal object AdManager {

    fun create(config: AdConfig): Ad {
        AdLog.i("Ad Create Config [$config]")
        return when (config.type) {
            AdType.AppOpen -> MaxAppOpenAd(config)
            AdType.Interstitial -> MaxInterstitialAd(config)
            AdType.Native -> MaxNativeAd(config)
            AdType.NativeBanner -> MaxBannerAd(config)
            AdType.RewardedInterstitial -> MaxRewardedAd(config)
            AdType.RewardedVideo -> MaxRewardedAd(config)
        }
    }

}