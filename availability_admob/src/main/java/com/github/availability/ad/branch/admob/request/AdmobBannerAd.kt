package com.github.availability.ad.branch.admob.request

import android.content.Context
import android.view.ViewGroup
import com.github.availability.ad.branch.admob.config.AdmobAdConfig
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdLifecycleView.Companion.createLifecycleView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

internal class AdmobBannerAd(override val config: AdConfig) : AdmobAdCompat<AdView>() {

    private fun adListener(result: Ad.AdCallback, adView: AdView) = object : AdListener() {
        override fun onAdFailedToLoad(error: LoadAdError) {
            completed(AdResult.Failure(AdFailure(error.code, error.message)), result, error)
        }

        override fun onAdLoaded() {
            completed(AdResult.Success(adView), result)
        }

        override fun onAdClicked() {
            simpleCallback.onClicked(this@AdmobBannerAd)
        }
    }

    override fun request(context: Context, callback: Ad.AdCallback) {
        val adSize = if (config is AdmobAdConfig) config.bannerSize else AdSize.BANNER
        val adView = AdView(context.applicationContext)
        adView.setAdSize(adSize)
        adView.adUnitId = config.id
        adView.loadAd(AdRequest(config.id))
        adView.adListener = adListener(callback, adView)
    }

    override fun showNative(rootView: ViewGroup) {
        val bannerView = valueOrNull ?: return
        bannerView.onPaidEventListener = paidEvent
        rootView.addView(createLifecycleView(rootView.context).addViews(bannerView))
    }

    override fun destroy() {
        val adView = valueOrNull
        adView?.onPaidEventListener = null
        adView?.destroy()
        adView?.removeAllViews()
        super.destroy()
    }

}