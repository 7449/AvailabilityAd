package com.github.availability.ad.branch.audience.request

import android.content.Context
import android.view.ViewGroup
import com.facebook.ads.AdError
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdListener
import com.github.availability.ad.branch.audience.view.NativeAdView
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class AudienceNativeAd(override val config: AdConfig) : AudienceAdCompat<NativeAd>() {

    override fun request(context: Context, callback: Ad.AdCallback) {
        with(NativeAd(context, config.id)) {
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
                            simpleCallback.onClicked(this@AudienceNativeAd)
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

    override fun showNative(rootView: ViewGroup) {
        val nativeAd = valueOrNull ?: return
        val adView = NativeAdView(rootView.context).apply {
            forNativeAd(nativeAd)
            simpleCallback.onPaidEvent(this@AudienceNativeAd, 0, "USD", "3")
        }.registerAd(this)
        rootView.addView(adView)
    }

    override fun destroy() {
        val nativeAd = valueOrNull
        nativeAd?.unregisterView()
        nativeAd?.destroy()
        super.destroy()
    }

}