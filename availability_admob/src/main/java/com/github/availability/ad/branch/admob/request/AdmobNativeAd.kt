package com.github.availability.ad.branch.admob.request

import android.content.Context
import android.view.ViewGroup
import com.github.availability.ad.branch.admob.view.NativeAdView
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

internal class AdmobNativeAd(override val config: AdConfig) : AdmobAdCompat<NativeAd>() {

    private fun nativeAdOptions(): NativeAdOptions {
        return NativeAdOptions.Builder()
            .setReturnUrlsForImageAssets(false)
            .setRequestMultipleImages(false)
            .setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
            .setVideoOptions(
                VideoOptions
                    .Builder()
                    .setStartMuted(true)
                    .build()
            )
            .build()
    }

    override fun request(context: Context, callback: Ad.AdCallback) {
        AdLoader.Builder(context, config.id)
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    completed(AdResult.Failure(AdFailure(error.code, error.message)), callback, error)
                }

                override fun onAdClicked() {
                    simpleCallback.onClicked(this@AdmobNativeAd)
                }
            }).withNativeAdOptions(nativeAdOptions())
            .forNativeAd { nativeAd: NativeAd -> completed(AdResult.Success(nativeAd), callback) }
            .build()
            .loadAd(AdRequest(config.id))
    }

    override fun showNative(rootView: ViewGroup) {
        val nativeAd = valueOrNull ?: return
        nativeAd.setOnPaidEventListener(paidEvent)
        rootView.addView(NativeAdView(rootView.context).forNativeAd(nativeAd).registerAd(this))
    }

    override fun destroy() {
        val nativeAd = valueOrNull
        nativeAd?.setOnPaidEventListener(null)
        nativeAd?.destroy()
        super.destroy()
    }

}