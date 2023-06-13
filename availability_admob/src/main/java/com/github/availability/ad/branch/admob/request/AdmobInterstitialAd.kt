package com.github.availability.ad.branch.admob.request

import android.content.Context
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

internal class AdmobInterstitialAd(override val config: AdConfig) :
    AdmobFullScreen<InterstitialAd>() {

    override fun request(context: Context, callback: Ad.AdCallback) {
        InterstitialAd.load(context, config.id,
            AdRequest(config.id),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    interstitialAd.setImmersiveMode(true)
                    completed(AdResult.Success(interstitialAd), callback)
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    completed(AdResult.Failure(AdFailure(error.code, error.message)), callback, error)
                }
            })
    }

}