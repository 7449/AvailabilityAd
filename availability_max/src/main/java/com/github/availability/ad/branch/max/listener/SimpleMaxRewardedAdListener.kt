package com.github.availability.ad.branch.max.listener

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxReward
import com.applovin.mediation.MaxRewardedAdListener

internal open class SimpleMaxRewardedAdListener : MaxRewardedAdListener {
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

    override fun onUserRewarded(ad: MaxAd, reward: MaxReward) {
    }

    @Deprecated("Deprecated in Java")
    override fun onRewardedVideoStarted(ad: MaxAd) {
    }

    @Deprecated("Deprecated in Java")
    override fun onRewardedVideoCompleted(ad: MaxAd) {
    }
}