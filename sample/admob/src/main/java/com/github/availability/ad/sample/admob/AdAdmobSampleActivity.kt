package com.github.availability.ad.sample.admob

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.core.os.bundleOf
import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.admob.AdmobAvailabilityAd
import com.github.availability.ad.branch.admob.config.AdmobAdConfig
import com.github.availability.ad.config.SimpleAdConfig
import com.github.availability.ad.core.AdType
import com.github.availability.ad.sample.ADMOB_APP_OPEN_ID
import com.github.availability.ad.sample.ADMOB_INTERSTITIAL_ID
import com.github.availability.ad.sample.ADMOB_INTERSTITIAL_VIDEO_ID
import com.github.availability.ad.sample.ADMOB_NATIVE_BANNER_COLLAPSIBLE_ID
import com.github.availability.ad.sample.ADMOB_NATIVE_ID
import com.github.availability.ad.sample.ADMOB_REWARDED_ID
import com.github.availability.ad.sample.ADMOB_REWARDED_INTERSTITIAL_ID
import com.github.availability.ad.sample.AvailabilityAdActivity
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdSize

class AdAdmobSampleActivity : AvailabilityAdActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AdmobAvailabilityAd.addNetworkExtrasBundle(
            arrayListOf(
                AdMobAdapter::class.java to bundleOf("collapsible" to "bottom")
            )
        )
        super.onCreate(savedInstanceState)
    }

    override val availabilityAd: AvailabilityAd
        get() = AdmobAvailabilityAd

    override val rewardedVideoConfig
        get() = SimpleAdConfig.noCache(ADMOB_REWARDED_ID, AdType.Interstitial)
    override val rewardedInterstitialConfig
        get() = SimpleAdConfig.noCache(ADMOB_REWARDED_INTERSTITIAL_ID, AdType.RewardedInterstitial)
    override val nativeConfig
        get() = SimpleAdConfig.successful(ADMOB_NATIVE_ID, AdType.Native, cacheMillis)
    override val interstitialConfig
        get() = SimpleAdConfig.noCache(ADMOB_INTERSTITIAL_ID, AdType.Interstitial)
    override val interstitialVideoConfig
        get() = SimpleAdConfig.noCache(ADMOB_INTERSTITIAL_VIDEO_ID, AdType.Interstitial)
    override val appOpenConfig
        get() = SimpleAdConfig.noCache(ADMOB_APP_OPEN_ID, AdType.AppOpen)
    override val bannerConfig
        get() = AdmobAdConfig(
            ADMOB_NATIVE_BANNER_COLLAPSIBLE_ID,
            AdType.NativeBanner,
            getAdSize()
        )

    private fun getAdSize(): AdSize {
        val displayMetrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
            this,
            (displayMetrics.widthPixels / displayMetrics.density).toInt()
        )
    }

}