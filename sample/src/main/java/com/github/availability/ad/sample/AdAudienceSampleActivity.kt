package com.github.availability.ad.sample

import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.audience.AudienceAvailabilityAd
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.SimpleAdConfig
import com.github.availability.ad.core.AdType
import java.util.concurrent.TimeUnit

class AdAudienceSampleActivity : AvailabilityAdActivity() {

    override val availabilityAd: AvailabilityAd
        get() = AudienceAvailabilityAd

    override val rewardedVideoConfig: AdConfig
        get() = SimpleAdConfig(
            AUDIENCE_ID, AdType.REWARDED_VIDEO, AUDIENCE_ID,
            true,
            true,
            TimeUnit.MINUTES.toMillis(60)
        )
    override val rewardedInterstitialConfig: AdConfig
        get() = SimpleAdConfig(
            AUDIENCE_ID, AdType.REWARDED_INTERSTITIAL, AUDIENCE_ID,
            true,
            true,
            TimeUnit.MINUTES.toMillis(60)
        )
    override val bannerConfig: AdConfig
        get() = SimpleAdConfig(
            AUDIENCE_ID, AdType.NATIVE_BANNER, AUDIENCE_ID,
            true,
            true,
            TimeUnit.MINUTES.toMillis(60)
        )
    override val nativeConfig: AdConfig
        get() = SimpleAdConfig(
            AUDIENCE_ID, AdType.NATIVE, AUDIENCE_ID,
            true,
            true,
            TimeUnit.MINUTES.toMillis(60)
        )
    override val appOpenConfig: AdConfig
        get() = SimpleAdConfig(
            AUDIENCE_ID, AdType.APP_OPEN, AUDIENCE_ID,
            true,
            true,
            TimeUnit.MINUTES.toMillis(60)
        )
    override val interstitialConfig: AdConfig
        get() = SimpleAdConfig(
            AUDIENCE_ID, AdType.INTERSTITIAL, AUDIENCE_ID,
            true,
            true,
            TimeUnit.MINUTES.toMillis(60)
        )
    override val interstitialVideoConfig: AdConfig
        get() = SimpleAdConfig(
            AUDIENCE_ID, AdType.INTERSTITIAL, AUDIENCE_ID,
            true,
            true,
            TimeUnit.MINUTES.toMillis(60)
        )

}