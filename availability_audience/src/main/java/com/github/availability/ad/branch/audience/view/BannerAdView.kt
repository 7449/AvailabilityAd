package com.github.availability.ad.branch.audience.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.facebook.ads.AdOptionsView
import com.facebook.ads.NativeBannerAd
import com.github.availability.ad.branch.audience.databinding.LayoutAudienceBannerAdBinding
import com.github.availability.ad.core.AvailabilityNativeView

internal class BannerAdView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AvailabilityNativeView(context, attrs, defStyleAttr) {

    private val viewBinding = LayoutAudienceBannerAdBinding.inflate(
        LayoutInflater.from(getContext()),
        this,
        true
    )

    override fun onDetachedFromWindow() {
        viewBinding.nativeIconView.destroy()
        super.onDetachedFromWindow()
    }

    fun forBannerAd(nativeAd: NativeBannerAd) {
        viewBinding.adChoicesContainer.removeAllViews()
        viewBinding.adChoicesContainer.addView(
            AdOptionsView(
                context,
                nativeAd,
                viewBinding.nativeBannerAdContainer,
                AdOptionsView.Orientation.HORIZONTAL,
                20
            )
        )
        inflateAd(nativeAd)
    }

    @SuppressLint("SetTextI18n")
    private fun inflateAd(nativeBannerAd: NativeBannerAd) {
        val nativeAdTitle = viewBinding.nativeAdTitle
        val nativeAdSocialContext = viewBinding.nativeAdSocialContext
        val sponsoredLabel = viewBinding.nativeAdSponsoredLabel
        val nativeAdIconView = viewBinding.nativeIconView
        val nativeAdCallToAction = viewBinding.nativeAdCallToAction
        nativeAdCallToAction.text = nativeBannerAd.adCallToAction
        nativeAdCallToAction.visibility =
            if (nativeBannerAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        nativeAdTitle.text = nativeBannerAd.advertiserName
        nativeAdSocialContext.text = nativeBannerAd.adSocialContext
        val clickableViews = ArrayList<View>()
        clickableViews.add(nativeAdCallToAction)
        nativeBannerAd.registerViewForInteraction(
            viewBinding.nativeBannerAdContainer,
            nativeAdIconView,
            clickableViews
        )
        sponsoredLabel.text = "Sponsored"
    }

}