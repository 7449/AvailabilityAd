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

    override val rewardedVideoConfig = SimpleAdConfig(
        MAX_REWARDED_ID,
        AdType.REWARDED_VIDEO
    )
    override val rewardedInterstitialConfig = SimpleAdConfig(
        MAX_REWARDED_INTERSTITIAL_ID,
        AdType.REWARDED_INTERSTITIAL
    )
    override val nativeConfig = SimpleAdConfig(
        MAX_NATIVE_ID,
        AdType.NATIVE
    )
    override val interstitialConfig = SimpleAdConfig(
        MAX_INTERSTITIAL_ID,
        AdType.INTERSTITIAL
    )
    override val interstitialVideoConfig = SimpleAdConfig(
        MAX_INTERSTITIAL_ID,
        AdType.INTERSTITIAL
    )
    override val appOpenConfig = SimpleAdConfig(
        MAX_APP_OPEN_ID,
        AdType.APP_OPEN
    )
    override val bannerConfig = SimpleAdConfig(
        MAX_NATIVE_BANNER_ID,
        AdType.NATIVE_BANNER
    )

}