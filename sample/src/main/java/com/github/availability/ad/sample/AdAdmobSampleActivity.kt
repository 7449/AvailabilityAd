package com.github.availability.ad.sample

import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.admob.AdmobAvailabilityAd
import com.github.availability.ad.branch.admob.config.AdmobAdConfig
import com.github.availability.ad.config.SimpleAdConfig
import com.github.availability.ad.core.AdType
import com.google.android.gms.ads.AdSize

class AdAdmobSampleActivity : AvailabilityAdActivity() {

    override val availabilityAd: AvailabilityAd
        get() = AdmobAvailabilityAd

    override val rewardedVideoConfig
        get() = SimpleAdConfig.noCache(ADMOB_REWARDED_ID, AdType.INTERSTITIAL)
    override val rewardedInterstitialConfig
        get() = SimpleAdConfig.noCache(ADMOB_REWARDED_INTERSTITIAL_ID, AdType.INTERSTITIAL)
    override val nativeConfig
        get() = SimpleAdConfig.successful(ADMOB_NATIVE_ID, AdType.NATIVE, cacheMillis)
    override val interstitialConfig
        get() = SimpleAdConfig.noCache(ADMOB_INTERSTITIAL_ID, AdType.INTERSTITIAL)
    override val interstitialVideoConfig
        get() = SimpleAdConfig.noCache(ADMOB_INTERSTITIAL_VIDEO_ID, AdType.INTERSTITIAL)
    override val appOpenConfig
        get() = SimpleAdConfig.noCache(ADMOB_APP_OPEN_ID, AdType.APP_OPEN)
    override val bannerConfig
        get() = AdmobAdConfig(ADMOB_NATIVE_BANNER_ID, AdType.NATIVE_BANNER, AdSize.LARGE_BANNER)

}