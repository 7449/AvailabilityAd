package com.github.availability.ad.branch.max.request

import android.content.Context
import android.view.ViewGroup
import com.applovin.impl.mediation.MaxErrorImpl
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxErrorCode
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder
import com.github.availability.ad.branch.max.R
import com.github.availability.ad.branch.max.listener.SimpleMaxNativeAdListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AvailabilityNativeView.Companion.createAvailabilityNativeView

internal class MaxNativeAd(override val config: AdConfig) : MaxAdCompat() {

    private var maxNativeAdView: MaxNativeAdView? = null
    private var maxNativeAdLoader: MaxNativeAdLoader? = null
    private val loadFailure = MaxErrorImpl(MaxErrorCode.NO_FILL, "MaxNativeAdView == null")

    private fun maxNativeAdView(context: Context) = MaxNativeAdView(
        MaxNativeAdViewBinder.Builder(R.layout.layout_max_native_ad)
            .setTitleTextViewId(R.id.ad_headline)
            .setBodyTextViewId(R.id.ad_body)
            .setIconImageViewId(R.id.ad_app_icon)
            .setMediaContentViewGroupId(R.id.ad_media)
            .setCallToActionButtonId(R.id.ad_call_to_action)
            .build(), context
    )

    override val isReady get() = maxNativeAdView != null

    override fun load(context: Context, callback: Ad.Callback) {
        super.load(context, callback)
        maxNativeAdLoader = MaxNativeAdLoader(config.id, context)
        maxNativeAdLoader?.setRevenueListener(revenueListener(context))
        maxNativeAdLoader?.setNativeAdListener(object : SimpleMaxNativeAdListener() {
            override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd) {
                if (nativeAdView == null) {
                    onNativeAdLoadFailed(config.id, loadFailure)
                    return
                }
                maxNativeAdView = nativeAdView
                completed(AdResult.Success(ad), callback)
            }

            override fun onNativeAdClicked(ad: MaxAd) {
                simpleCallback.onClicked(this@MaxNativeAd)
            }

            override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
                completed(AdResult.Failure(AdFailure(error.code, error.message)), callback)
            }
        })
        maxNativeAdLoader?.loadAd(maxNativeAdView(context))
    }

    override fun show(rootView: ViewGroup) {
        val nativeAdView = maxNativeAdView ?: return
        rootView.addView(createAvailabilityNativeView(rootView.context).addViews(nativeAdView))
    }

    override fun destroy() {
        val maxAd = valueOrNull
        if (maxAd == null) {
            maxNativeAdLoader?.destroy()
        } else {
            maxNativeAdLoader?.destroy(maxAd)
        }
        maxNativeAdLoader = null
        maxNativeAdView = null
        super.destroy()
    }

}