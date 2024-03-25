package com.github.availability.ad.branch.max.listener

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError

internal open class SimpleMaxAdViewListener : MaxAdViewAdListener {
    override fun onAdLoaded(ad: MaxAd) {
    }

    override fun onAdDisplayed(ad: MaxAd) {
    }

    override fun onAdHidden(ad: MaxAd) {
    }

    override fun onAdClicked(ad: MaxAd) {
    }

    override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
    }

    override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
    }

    override fun onAdExpanded(ad: MaxAd) {
    }

    override fun onAdCollapsed(ad: MaxAd) {
    }
}