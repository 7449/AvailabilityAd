package com.github.availability.ad.branch.max.request

import android.app.Activity
import android.content.Context
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAppOpenAd
import com.github.availability.ad.branch.max.listener.SimpleMaxAdListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class MaxAppOpenAd(override val config: AdConfig) : MaxAdCompat() {

    private var maxAppOpenAd: MaxAppOpenAd? = null

    override val isReady get() = maxAppOpenAd?.isReady == true

    override fun request(context: Context, callback: Ad.AdCallback) {
        maxAppOpenAd = MaxAppOpenAd(config.id, context)
        maxAppOpenAd?.setRevenueListener(revenueListener(context))
        maxAppOpenAd?.setListener(object : SimpleMaxAdListener() {
            override fun onAdLoaded(ad: MaxAd) {
                completed(AdResult.Success(ad), callback)
            }

            override fun onAdClicked(ad: MaxAd) {
                simpleCallback.onClicked(this@MaxAppOpenAd)
            }

            override fun onAdDisplayed(ad: MaxAd) {
                simpleCallback.onShowedFullScreenContent(this@MaxAppOpenAd)
            }

            override fun onAdHidden(ad: MaxAd) {
                simpleCallback.onDismissedFullScreenContent(this@MaxAppOpenAd)
                destroy()
            }

            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                simpleCallback.onFailedToShowFullScreenContent(this@MaxAppOpenAd, error.code)
                destroy()
            }

            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                completed(AdResult.Failure(AdFailure(error.code, error.message)), callback, error)
            }
        })
        maxAppOpenAd?.loadAd()
    }

    override fun showFullScreen(activity: Activity) {
        maxAppOpenAd?.showAd()
    }

    override fun destroy() {
        maxAppOpenAd?.setListener(null)
        maxAppOpenAd?.destroy()
        maxAppOpenAd = null
        super.destroy()
    }

}