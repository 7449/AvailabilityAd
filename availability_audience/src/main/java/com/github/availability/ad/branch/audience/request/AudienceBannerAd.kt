package com.github.availability.ad.branch.audience.request

import android.content.Context
import android.view.ViewGroup
import com.facebook.ads.AdError
import com.facebook.ads.NativeAdListener
import com.facebook.ads.NativeBannerAd
import com.github.availability.ad.branch.audience.view.BannerAdView
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class AudienceBannerAd(override val config: AdConfig) :
    AudienceAdCompat<NativeBannerAd>() {

    override fun load(context: Context, callback: Ad.Callback) {
        super.load(context, callback)
        with(NativeBannerAd(context, config.id)) {
            loadAd(
                buildLoadAdConfig()
                    .withAdListener(object : NativeAdListener {
                        override fun onError(ad: com.facebook.ads.Ad, error: AdError) {
                            ad.destroy()
                            completed(
                                AdResult.Failure(AdFailure(error.errorCode, error.errorMessage)),
                                callback
                            )
                        }

                        override fun onAdLoaded(ad: com.facebook.ads.Ad) {
                            completed(AdResult.Success(this@with), callback)
                        }

                        override fun onAdClicked(ad: com.facebook.ads.Ad) {
                            simpleCallback.onClicked(this@AudienceBannerAd)
                        }

                        override fun onLoggingImpression(ad: com.facebook.ads.Ad) {
                        }

                        override fun onMediaDownloaded(ad: com.facebook.ads.Ad) {
                        }

                    })
                    .build()
            )
        }
    }

    override fun show(rootView: ViewGroup) {
        val bannerAd = valueOrNull ?: return
        val adView = BannerAdView(rootView.context).apply {
            forBannerAd(bannerAd)
            simpleCallback.onPaidEvent(this@AudienceBannerAd, 0, "USD", "3")
        }.registerAd(this)
        rootView.addView(adView)
    }

    override fun destroy() {
        val nativeBannerAd = valueOrNull
        nativeBannerAd?.unregisterView()
        nativeBannerAd?.destroy()
        super.destroy()
    }

}