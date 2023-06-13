package com.github.availability.ad.branch.audience.request

import android.app.Activity
import android.content.Context
import com.facebook.ads.AdError
import com.facebook.ads.RewardedInterstitialAd
import com.facebook.ads.RewardedInterstitialAdListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class AudienceRewardedInterstitialAd(override val config: AdConfig) :
    AudienceAdCompat<RewardedInterstitialAd>() {

    override fun request(context: Context, callback: Ad.AdCallback) {
        with(RewardedInterstitialAd(context, config.id)) {
            loadAd(
                buildLoadAdConfig()
                    .withFailOnCacheFailureEnabled(true)
                    .withAdListener(object : RewardedInterstitialAdListener {
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
                            simpleCallback.onClicked(this@AudienceRewardedInterstitialAd)
                        }

                        override fun onLoggingImpression(ad: com.facebook.ads.Ad) {
                            simpleCallback.onShowedFullScreenContent(this@AudienceRewardedInterstitialAd)
                            simpleCallback.onPaidEvent(
                                this@AudienceRewardedInterstitialAd,
                                0,
                                "USD",
                                "3"
                            )
                        }

                        override fun onRewardedInterstitialCompleted() {
                            simpleCallback.rewarded(
                                this@AudienceRewardedInterstitialAd,
                                "",
                                0
                            )
                        }

                        override fun onRewardedInterstitialClosed() {
                            simpleCallback.onDismissedFullScreenContent(this@AudienceRewardedInterstitialAd)
                            this@AudienceRewardedInterstitialAd.destroy()
                        }
                    })
                    .build()
            )
        }
    }

    override fun showFullScreen(activity: Activity) {
        valueOrNull?.show()
    }

    override fun destroy() {
        val rewardedInterstitialAd = valueOrNull
        rewardedInterstitialAd?.destroy()
        super.destroy()
    }

}