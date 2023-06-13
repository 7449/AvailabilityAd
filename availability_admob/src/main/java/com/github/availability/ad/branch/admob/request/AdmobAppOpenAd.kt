package com.github.availability.ad.branch.admob.request

import android.content.Context
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd

internal class AdmobAppOpenAd(override val config: AdConfig) : AdmobFullScreen<AppOpenAd>() {

    override fun request(context: Context, callback: Ad.AdCallback) {
        AppOpenAd.load(
            context,
            config.id,
            AdRequest(config.id),
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(appOpenAd: AppOpenAd) {
                    appOpenAd.setImmersiveMode(true)
                    completed(AdResult.Success(appOpenAd), callback)
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    completed(AdResult.Failure(AdFailure(error.code, error.message)), callback)
                }
            })
    }

}