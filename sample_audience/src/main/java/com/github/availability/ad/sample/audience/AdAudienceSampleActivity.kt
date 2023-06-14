package com.github.availability.ad.sample.audience

import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.audience.AudienceAvailabilityAd
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.SimpleAdConfig
import com.github.availability.ad.core.AdType
import com.github.availability.ad.sample.AUDIENCE_ID
import com.github.availability.ad.sample.AvailabilityAdActivity

class AdAudienceSampleActivity : AvailabilityAdActivity() {

    override val availabilityAd: AvailabilityAd
        get() = AudienceAvailabilityAd

    override val rewardedVideoConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.REWARDED_VIDEO, cacheMillis)
    override val rewardedInterstitialConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.REWARDED_INTERSTITIAL, cacheMillis)
    override val bannerConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.NATIVE_BANNER, cacheMillis)
    override val nativeConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.NATIVE, cacheMillis)
    override val appOpenConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.APP_OPEN, cacheMillis)
    override val interstitialConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.INTERSTITIAL, cacheMillis)
    override val interstitialVideoConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.INTERSTITIAL, cacheMillis)
}