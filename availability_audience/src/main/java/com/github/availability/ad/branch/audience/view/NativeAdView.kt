package com.github.availability.ad.branch.audience.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.facebook.ads.AdOptionsView
import com.facebook.ads.NativeAd
import com.facebook.ads.NativeAdBase
import com.github.availability.ad.branch.audience.databinding.LayoutAudienceNativeAdBinding
import com.github.availability.ad.core.AdLifecycleView

internal class NativeAdView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AdLifecycleView(context, attrs, defStyleAttr) {

    private val viewBinding =
        LayoutAudienceNativeAdBinding.inflate(LayoutInflater.from(getContext()), this, true)

    override fun onDetachedFromWindow() {
        viewBinding.nativeAdMedia.destroy()
        viewBinding.nativeAdIcon.destroy()
        super.onDetachedFromWindow()
    }

    fun forNativeAd(nativeAd: NativeAd) {
        viewBinding.adChoicesContainer.removeAllViews()
        viewBinding.adChoicesContainer.addView(
            AdOptionsView(
                context,
                nativeAd,
                viewBinding.nativeAdLayout
            ), 0
        )
        inflateAd(nativeAd)
    }

    @SuppressLint("SetTextI18n")
    private fun inflateAd(nativeAd: NativeAd) {
        val nativeAdIcon = viewBinding.nativeAdIcon
        val nativeAdTitle = viewBinding.nativeAdTitle
        val nativeAdBody = viewBinding.nativeAdBody
        val sponsoredLabel = viewBinding.nativeAdSponsoredLabel
        val nativeAdSocialContext = viewBinding.nativeAdSocialContext
        val nativeAdCallToAction = viewBinding.nativeAdCallToAction
        val nativeAdMedia = viewBinding.nativeAdMedia
        nativeAdSocialContext.text = nativeAd.adSocialContext
        nativeAdCallToAction.text = nativeAd.adCallToAction
        nativeAdCallToAction.visibility =
            if (nativeAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        nativeAdTitle.text = nativeAd.advertiserName
        nativeAdBody.text = nativeAd.adBodyText
        sponsoredLabel.text = "Sponsored"
        val clickableViews = ArrayList<View>()
        clickableViews.add(nativeAdIcon)
        clickableViews.add(nativeAdMedia)
        clickableViews.add(nativeAdCallToAction)
        nativeAd.registerViewForInteraction(
            viewBinding.nativeAdLayout,
            nativeAdMedia,
            nativeAdIcon,
            clickableViews
        )

        NativeAdBase.NativeComponentTag.tagView(
            nativeAdIcon,
            NativeAdBase.NativeComponentTag.AD_ICON
        )
        NativeAdBase.NativeComponentTag.tagView(
            nativeAdTitle,
            NativeAdBase.NativeComponentTag.AD_TITLE
        )
        NativeAdBase.NativeComponentTag.tagView(
            nativeAdBody,
            NativeAdBase.NativeComponentTag.AD_BODY
        )
        NativeAdBase.NativeComponentTag.tagView(
            nativeAdSocialContext, NativeAdBase.NativeComponentTag.AD_SOCIAL_CONTEXT
        )
        NativeAdBase.NativeComponentTag.tagView(
            nativeAdCallToAction, NativeAdBase.NativeComponentTag.AD_CALL_TO_ACTION
        )
    }

}