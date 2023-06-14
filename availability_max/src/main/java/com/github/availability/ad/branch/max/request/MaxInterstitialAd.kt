package com.github.availability.ad.branch.max.request

import android.app.Activity
import android.content.Context
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxErrorCode
import com.applovin.mediation.ads.MaxInterstitialAd
import com.github.availability.ad.branch.max.listener.SimpleMaxAdListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class MaxInterstitialAd(override val config: AdConfig) : MaxAdCompat() {

    private val contextFailure = AdFailure(MaxErrorCode.UNSPECIFIED, "context is activity")

    private var maxInterstitialAd: MaxInterstitialAd? = null

    override val isReady get() = maxInterstitialAd?.isReady == true

    override fun load(context: Context, callback: Ad.Callback) {
        super.load(context, callback)
        if (context !is Activity) {
            completed(AdResult.Failure(contextFailure), callback)
            return
        }
        maxInterstitialAd = MaxInterstitialAd(config.id, context)
        maxInterstitialAd?.setRevenueListener(revenueListener(context))
        maxInterstitialAd?.setListener(object : SimpleMaxAdListener() {
            override fun onAdLoaded(ad: MaxAd) {
                completed(AdResult.Success(ad), callback)
            }

            override fun onAdClicked(ad: MaxAd) {
                simpleCallback.onClicked(this@MaxInterstitialAd)
            }

            override fun onAdDisplayed(ad: MaxAd) {
                simpleCallback.onShowedFullScreen(this@MaxInterstitialAd)
            }

            override fun onAdHidden(ad: MaxAd) {
                simpleCallback.onDismissedFullScreen(this@MaxInterstitialAd)
                destroy()
            }

            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                simpleCallback.onFailedToShowFullScreen(this@MaxInterstitialAd, error.code)
                destroy()
            }

            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                completed(AdResult.Failure(AdFailure(error.code, error.message)), callback)
            }
        })
        maxInterstitialAd?.loadAd()
    }

    override fun show(activity: Activity) {
        maxInterstitialAd?.showAd()
    }

    override fun destroy() {
        maxInterstitialAd?.setListener(null)
        maxInterstitialAd?.destroy()
        maxInterstitialAd = null
        super.destroy()
    }

}