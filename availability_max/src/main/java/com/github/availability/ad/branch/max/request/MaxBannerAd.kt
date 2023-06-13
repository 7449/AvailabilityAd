package com.github.availability.ad.branch.max.request

import android.content.Context
import android.view.ViewGroup
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.github.availability.ad.branch.max.listener.SimpleMaxAdViewListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdLifecycleView.Companion.createLifecycleView

internal class MaxBannerAd(override val config: AdConfig) : MaxAdCompat() {

    private var maxAdView: MaxAdView? = null

    override val isReady get() = maxAdView != null

    override fun request(context: Context, callback: Ad.AdCallback) {
        maxAdView = MaxAdView(config.id, context)
        maxAdView?.setRevenueListener(revenueListener(context))
        maxAdView?.setListener(object : SimpleMaxAdViewListener() {
            override fun onAdLoaded(ad: MaxAd) {
                completed(AdResult.Success(ad), callback)
            }

            override fun onAdClicked(ad: MaxAd) {
                simpleCallback.onClicked(this@MaxBannerAd)
            }

            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                completed(AdResult.Failure(AdFailure(error.code, error.message)), callback, error)
            }
        })
        maxAdView?.loadAd()
    }

    override fun showNative(rootView: ViewGroup) {
        val maxAdView = maxAdView ?: return
        rootView.addView(createLifecycleView(rootView.context).addViews(maxAdView))
    }

    override fun destroy() {
        maxAdView?.destroy()
        maxAdView = null
        super.destroy()
    }

}