package com.github.availability.ad.branch.admob.request

import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdLog
import com.github.availability.ad.core.AdType

internal object AdManager {

    fun create(config: AdConfig): Ad {
        AdLog.i("Ad Create Config [$config]")
        return when (config.type) {
            AdType.AppOpen -> AdmobAppOpenAd(config)
            AdType.Interstitial -> AdmobInterstitialAd(config)
            AdType.Native -> AdmobNativeAd(config)
            AdType.NativeBanner -> AdmobBannerAd(config)
            AdType.RewardedInterstitial -> AdmobRewardedInterstitialAd(config)
            AdType.RewardedVideo -> AdmobRewardedVideoAd(config)
        }
    }

}