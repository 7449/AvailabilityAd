package com.github.availability.ad.branch.audience.request

import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdLog
import com.github.availability.ad.core.AdType

internal object AdManager {

    fun create(config: AdConfig): Ad {
        AdLog.i("Ad Create Config [$config]")
        return when (config.type) {
            AdType.AppOpen -> AudienceInterstitialAd(config)
            AdType.Interstitial -> AudienceInterstitialAd(config)
            AdType.Native -> AudienceNativeAd(config)
            AdType.NativeBanner -> AudienceBannerAd(config)
            AdType.RewardedInterstitial -> AudienceRewardedInterstitialAd(config)
            AdType.RewardedVideo -> AudienceRewardedVideoAd(config)
        }
    }

}