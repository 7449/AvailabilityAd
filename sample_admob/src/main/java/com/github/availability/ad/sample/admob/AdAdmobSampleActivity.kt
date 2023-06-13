package com.github.availability.ad.sample.admob

import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.admob.AdmobAvailabilityAd
import com.github.availability.ad.branch.admob.config.AdmobAdConfig
import com.github.availability.ad.core.AdType
import com.github.availability.ad.sample.ADMOB_APP_OPEN_ID
import com.github.availability.ad.sample.ADMOB_INTERSTITIAL_ID
import com.github.availability.ad.sample.ADMOB_INTERSTITIAL_VIDEO_ID
import com.github.availability.ad.sample.ADMOB_NATIVE_BANNER_ID
import com.github.availability.ad.sample.ADMOB_NATIVE_ID
import com.github.availability.ad.sample.ADMOB_REWARDED_ID
import com.github.availability.ad.sample.ADMOB_REWARDED_INTERSTITIAL_ID
import com.github.availability.ad.sample.AvailabilityAdActivity
import com.google.android.gms.ads.AdSize

class AdAdmobSampleActivity : AvailabilityAdActivity() {

    override val availabilityAd: AvailabilityAd
        get() = AdmobAvailabilityAd

    override val rewardedVideoConfig = AdmobAdConfig(
        ADMOB_REWARDED_ID,
        AdType.INTERSTITIAL
    )
    override val rewardedInterstitialConfig = AdmobAdConfig(
        ADMOB_REWARDED_INTERSTITIAL_ID,
        AdType.INTERSTITIAL
    )
    override val nativeConfig = AdmobAdConfig(
        ADMOB_NATIVE_ID,
        AdType.NATIVE
    )
    override val interstitialConfig = AdmobAdConfig(
        ADMOB_INTERSTITIAL_ID,
        AdType.INTERSTITIAL
    )
    override val interstitialVideoConfig = AdmobAdConfig(
        ADMOB_INTERSTITIAL_VIDEO_ID,
        AdType.INTERSTITIAL
    )
    override val appOpenConfig = AdmobAdConfig(
        ADMOB_APP_OPEN_ID,
        AdType.APP_OPEN
    )
    override val bannerConfig = AdmobAdConfig(
        ADMOB_NATIVE_BANNER_ID,
        AdType.NATIVE_BANNER, "", false, false, -1, AdSize.LARGE_BANNER
    )

}