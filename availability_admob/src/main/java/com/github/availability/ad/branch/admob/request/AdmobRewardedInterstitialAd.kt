package com.github.availability.ad.branch.admob.request

import android.content.Context
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback

internal class AdmobRewardedInterstitialAd(override val config: AdConfig) :
    AdmobFullScreen<RewardedInterstitialAd>() {

    override fun load(context: Context, callback: Ad.Callback) {
        super.load(context, callback)
        RewardedInterstitialAd.load(context, config.id,
            AdRequest(config.id),
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    ad.setImmersiveMode(true)
                    completed(AdResult.Success(ad), callback)
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    completed(AdResult.Failure(AdFailure(error.code, error.message)), callback)
                }
            })
    }

}