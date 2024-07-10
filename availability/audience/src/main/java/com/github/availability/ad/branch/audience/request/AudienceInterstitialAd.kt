package com.github.availability.ad.branch.audience.request

import android.app.Activity
import android.content.Context
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class AudienceInterstitialAd(override val config: AdConfig) :
    AudienceAdCompat<InterstitialAd>() {

    override fun load(context: Context, callback: Ad.Callback) {
        super.load(context, callback)
        with(InterstitialAd(context, config.id)) {
            loadAd(
                buildLoadAdConfig()
                    .withAdListener(object : InterstitialAdListener {
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
                            simpleCallback.onClicked(this@AudienceInterstitialAd)
                        }

                        override fun onLoggingImpression(ad: com.facebook.ads.Ad) {
                        }

                        override fun onInterstitialDismissed(p0: com.facebook.ads.Ad?) {
                            simpleCallback.onDismissedFullScreen(this@AudienceInterstitialAd)
                            this@AudienceInterstitialAd.destroy()
                        }

                        override fun onInterstitialDisplayed(p0: com.facebook.ads.Ad?) {
                            simpleCallback.onShowedFullScreen(this@AudienceInterstitialAd)
                            simpleCallback.onPaidEvent(this@AudienceInterstitialAd, 0, "USD", "3")
                        }
                    })
                    .build()
            )
        }
    }

    override fun show(activity: Activity) {
        valueOrNull?.show()
    }

    override fun show() {
        valueOrNull?.show()
    }

    override fun destroy() {
        val interstitialAd = valueOrNull
        interstitialAd?.destroy()
        super.destroy()
    }

}