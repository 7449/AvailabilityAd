package com.github.availability.ad.branch.admob.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.github.availability.ad.branch.admob.R
import com.github.availability.ad.core.AvailabilityNativeView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

internal class NativeAdView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AvailabilityNativeView(context, attrs, defStyleAttr) {

    private val adView by lazy { findViewById<NativeAdView>(R.id.native_view) }

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_admob_native_ad, this, true)
    }

    private var View.isInvisible: Boolean
        get() = visibility == View.INVISIBLE
        set(value) {
            visibility = if (value) View.INVISIBLE else View.VISIBLE
        }

    private fun initAdViews(nativeAd: NativeAd) {
        adView.mediaView = adView.findViewById(R.id.ad_media)
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)

        (adView.headlineView as TextView?)?.text = nativeAd.headline
        adView.mediaView?.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        nativeAd.mediaContent?.let { adView.mediaView?.setMediaContent(it) }

        adView.bodyView?.isInvisible = nativeAd.body == null
        nativeAd.body?.let { (adView.bodyView as TextView?)?.text = it }

        adView.callToActionView?.isInvisible = nativeAd.callToAction == null
        nativeAd.callToAction?.let { (adView.callToActionView as TextView?)?.text = it }

        adView.priceView?.isInvisible = nativeAd.price == null
        nativeAd.price?.let { (adView.priceView as TextView?)?.text = it }

        adView.storeView?.isInvisible = nativeAd.store == null
        nativeAd.store?.let { (adView.storeView as TextView?)?.text = it }

        adView.starRatingView?.isInvisible = nativeAd.starRating == null
        nativeAd.starRating?.let { (adView.starRatingView as RatingBar?)?.rating = it.toFloat() }

        adView.advertiserView?.isInvisible = nativeAd.advertiser == null
        nativeAd.advertiser?.let { (adView.advertiserView as TextView?)?.text = it }

        adView.iconView?.isInvisible = nativeAd.icon == null
        nativeAd.icon?.let { (adView.iconView as ImageView?)?.setImageDrawable(it.drawable) }
    }

    fun forNativeAd(nativeAd: NativeAd) = apply {
        initAdViews(nativeAd)
        adView.setNativeAd(nativeAd)
    }

    override fun onDetachedFromWindow() {
        adView.destroy()
        super.onDetachedFromWindow()
    }

}