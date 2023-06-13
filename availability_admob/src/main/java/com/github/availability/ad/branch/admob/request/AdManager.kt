package com.github.availability.ad.branch.admob.request

import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdType
import com.github.availability.ad.debug.AdLog

internal object AdManager {

    fun create(config: AdConfig): Ad {
        AdLog.i("Ad Create Config [$config]")
        return when (config.type) {
            AdType.NATIVE -> AdmobNativeAd(config)
            AdType.INTERSTITIAL -> AdmobInterstitialAd(config)
            AdType.APP_OPEN -> AdmobAppOpenAd(config)
            AdType.NATIVE_BANNER -> AdmobBannerAd(config)
            AdType.REWARDED_VIDEO -> AdmobRewardedVideoAd(config)
            AdType.REWARDED_INTERSTITIAL -> AdmobRewardedInterstitialAd(config)
        }
    }

}