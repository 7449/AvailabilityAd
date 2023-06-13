package com.github.availability.ad.branch.admob.request

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd

internal abstract class AdmobFullScreen<AD> : AdmobAdCompat<AD>() {

    private val fullScreenCallback = object : FullScreenContentCallback() {
        override fun onAdClicked() {
            simpleCallback.onClicked(this@AdmobFullScreen)
        }

        override fun onAdDismissedFullScreenContent() {
            simpleCallback.onDismissedFullScreenContent(this@AdmobFullScreen)
            destroy()
        }

        override fun onAdFailedToShowFullScreenContent(error: AdError) {
            simpleCallback.onFailedToShowFullScreenContent(this@AdmobFullScreen, error.code)
            destroy()
        }

        override fun onAdShowedFullScreenContent() {
            simpleCallback.onShowedFullScreenContent(this@AdmobFullScreen)
        }
    }

    override fun showFullScreen(activity: Activity) {
        when (val fullScreen = valueOrNull) {
            is AppOpenAd -> fullScreen.let {
                it.onPaidEventListener = paidEvent
                it.fullScreenContentCallback = fullScreenCallback
                it.show(activity)
            }

            is InterstitialAd -> fullScreen.let {
                it.onPaidEventListener = paidEvent
                it.fullScreenContentCallback = fullScreenCallback
                it.show(activity)
            }

            is RewardedAd -> fullScreen.let { it ->
                it.onPaidEventListener = paidEvent
                it.fullScreenContentCallback = fullScreenCallback
                it.show(activity) {
                    simpleCallback.rewarded(this, it.type, it.amount)
                }
            }

            is RewardedInterstitialAd -> fullScreen.let { it ->
                it.onPaidEventListener = paidEvent
                it.fullScreenContentCallback = fullScreenCallback
                it.show(activity) {
                    simpleCallback.rewarded(this, it.type, it.amount)
                }
            }
        }
    }

    override fun destroy() {
        when (val fullScreen = valueOrNull) {
            is AppOpenAd -> fullScreen.let {
                it.onPaidEventListener = null
                it.fullScreenContentCallback = null
            }

            is InterstitialAd -> fullScreen.let {
                it.onPaidEventListener = null
                it.fullScreenContentCallback = null
            }

            is RewardedAd -> fullScreen.let {
                it.onPaidEventListener = null
                it.fullScreenContentCallback = null
            }

            is RewardedInterstitialAd -> fullScreen.let {
                it.onPaidEventListener = null
                it.fullScreenContentCallback = null
            }
        }
        super.destroy()
    }

}