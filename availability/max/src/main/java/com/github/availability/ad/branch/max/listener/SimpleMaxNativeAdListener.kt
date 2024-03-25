package com.github.availability.ad.branch.max.listener

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdView

internal open class SimpleMaxNativeAdListener : MaxNativeAdListener() {
    override fun onNativeAdClicked(ad: MaxAd) {
    }

    override fun onNativeAdExpired(ad: MaxAd) {
    }

    override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
    }

    override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd) {
    }
}