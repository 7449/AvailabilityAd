package com.github.availability.ad.sample.max

import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.max.MaxAvailabilityAd
import com.github.availability.ad.config.SimpleAdConfig
import com.github.availability.ad.core.AdType
import com.github.availability.ad.sample.AvailabilityAdActivity
import com.github.availability.ad.sample.MAX_APP_OPEN_ID
import com.github.availability.ad.sample.MAX_INTERSTITIAL_ID
import com.github.availability.ad.sample.MAX_NATIVE_BANNER_ID
import com.github.availability.ad.sample.MAX_NATIVE_ID
import com.github.availability.ad.sample.MAX_REWARDED_ID
import com.github.availability.ad.sample.MAX_REWARDED_INTERSTITIAL_ID

class AdMaxSampleActivity : AvailabilityAdActivity() {

    override val availabilityAd: AvailabilityAd
        get() = MaxAvailabilityAd

    override val rewardedVideoConfig
        get() = SimpleAdConfig.noCache(MAX_REWARDED_ID, AdType.RewardedVideo)
    override val rewardedInterstitialConfig
        get() = SimpleAdConfig.noCache(MAX_REWARDED_INTERSTITIAL_ID, AdType.RewardedInterstitial)
    override val nativeConfig
        get() = SimpleAdConfig.noCache(MAX_NATIVE_ID, AdType.Native)
    override val interstitialConfig
        get() = SimpleAdConfig.noCache(MAX_INTERSTITIAL_ID, AdType.Interstitial)
    override val interstitialVideoConfig
        get() = SimpleAdConfig.noCache(MAX_INTERSTITIAL_ID, AdType.Interstitial)
    override val appOpenConfig
        get() = SimpleAdConfig.noCache(MAX_APP_OPEN_ID, AdType.AppOpen)
    override val bannerConfig
        get() = SimpleAdConfig.noCache(MAX_NATIVE_BANNER_ID, AdType.NativeBanner)

}