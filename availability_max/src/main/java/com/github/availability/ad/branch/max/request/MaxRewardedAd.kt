package com.github.availability.ad.branch.max.request

import android.app.Activity
import android.content.Context
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxErrorCode
import com.applovin.mediation.MaxReward
import com.applovin.mediation.ads.MaxRewardedAd
import com.github.availability.ad.branch.max.listener.SimpleMaxRewardedAdListener
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal class MaxRewardedAd(override val config: AdConfig) : MaxAdCompat() {

    private val contextFailure = AdFailure(MaxErrorCode.UNSPECIFIED, "context is activity")
    private var maxRewardedAd: MaxRewardedAd? = null

    override val isReady get() = maxRewardedAd?.isReady == true

    override fun load(context: Context, callback: Ad.Callback) {
        super.load(context, callback)
        if (context !is Activity) {
            completed(AdResult.Failure(contextFailure), callback)
            return
        }
        maxRewardedAd = MaxRewardedAd.getInstance(config.id, context)
        maxRewardedAd?.setRevenueListener(revenueListener(context))
        maxRewardedAd?.setListener(object : SimpleMaxRewardedAdListener() {
            override fun onAdLoaded(ad: MaxAd) {
                completed(AdResult.Success(ad), callback)
            }

            override fun onAdClicked(ad: MaxAd) {
                simpleCallback.onClicked(this@MaxRewardedAd)
            }

            override fun onAdDisplayed(ad: MaxAd) {
                simpleCallback.onShowedFullScreen(this@MaxRewardedAd)
            }

            override fun onAdHidden(ad: MaxAd) {
                simpleCallback.onDismissedFullScreen(this@MaxRewardedAd)
                destroy()
            }

            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                simpleCallback.onFailedToShowFullScreen(this@MaxRewardedAd, error.code)
                destroy()
            }

            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                completed(AdResult.Failure(AdFailure(error.code, error.message)), callback)
            }

            override fun onUserRewarded(ad: MaxAd, reward: MaxReward) {
                simpleCallback.onRewarded(this@MaxRewardedAd, reward.label, reward.amount)
            }
        })
        maxRewardedAd?.loadAd()
    }

    override fun show(activity: Activity) {
        maxRewardedAd?.showAd()
    }

    override fun show() {
        maxRewardedAd?.showAd()
    }

    override fun destroy() {
        maxRewardedAd?.setListener(null)
        maxRewardedAd?.destroy()
        maxRewardedAd = null
        super.destroy()
    }

}