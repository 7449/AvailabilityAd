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
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.RewardedVideo, cacheMillis)
    override val rewardedInterstitialConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.RewardedInterstitial, cacheMillis)
    override val bannerConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.NativeBanner, cacheMillis)
    override val nativeConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.Native, cacheMillis)
    override val appOpenConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.AppOpen, cacheMillis)
    override val interstitialConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.Interstitial, cacheMillis)
    override val interstitialVideoConfig: AdConfig
        get() = SimpleAdConfig.successful(AUDIENCE_ID, AdType.Interstitial, cacheMillis)
}