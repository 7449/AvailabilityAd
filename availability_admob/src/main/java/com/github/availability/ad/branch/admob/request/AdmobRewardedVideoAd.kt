package com.github.availability.ad.branch.admob.request

import android.content.Context
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

internal class AdmobRewardedVideoAd(override val config: AdConfig) : AdmobFullScreen<RewardedAd>() {

    override fun request(context: Context, callback: Ad.AdCallback) {
        RewardedAd.load(context, config.id,
            AdRequest(config.id),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    ad.setImmersiveMode(true)
                    completed(AdResult.Success(ad), callback)
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    completed(AdResult.Failure(AdFailure(error.code, error.message)), callback)
                }
            })
    }

}