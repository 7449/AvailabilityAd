package com.github.availability.ad.branch.audience.request

import android.app.Activity
import android.content.Context
import com.facebook.ads.AdError
import com.facebook.ads.RewardedVideoAd
import com.facebook.ads.RewardedVideoAdListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class AudienceRewardedVideoAd(override val config: AdConfig) :
    AudienceAdCompat<RewardedVideoAd>() {

    override fun load(context: Context, callback: Ad.Callback) {
        super.load(context, callback)
        with(RewardedVideoAd(context, config.id)) {
            loadAd(
                buildLoadAdConfig()
                    .withFailOnCacheFailureEnabled(true)
                    .withAdListener(object : RewardedVideoAdListener {
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
                            simpleCallback.onClicked(this@AudienceRewardedVideoAd)
                        }

                        override fun onLoggingImpression(ad: com.facebook.ads.Ad) {
                            simpleCallback.onShowedFullScreen(this@AudienceRewardedVideoAd)
                            simpleCallback.onPaidEvent(this@AudienceRewardedVideoAd, 0, "USD", "3")
                        }

                        override fun onRewardedVideoCompleted() {
                            simpleCallback.onRewarded(this@AudienceRewardedVideoAd, "", 0)
                        }

                        override fun onRewardedVideoClosed() {
                            simpleCallback.onDismissedFullScreen(this@AudienceRewardedVideoAd)
                            this@AudienceRewardedVideoAd.destroy()
                        }
                    })
                    .build()
            )
        }
    }

    override fun show(activity: Activity) {
        valueOrNull?.show()
    }

    override fun destroy() {
        val rewardedVideoAd = valueOrNull
        rewardedVideoAd?.unregisterAdCompanionView()
        rewardedVideoAd?.destroy()
        super.destroy()
    }

}