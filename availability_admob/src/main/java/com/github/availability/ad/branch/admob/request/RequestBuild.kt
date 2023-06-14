package com.github.availability.ad.branch.admob.request

import android.os.Bundle
import com.github.availability.ad.branch.admob.AdmobAvailabilityAd
import com.github.availability.ad.debug.AdDebug
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.OnPaidEventListener

internal val testVideoId =
    arrayListOf(
        "ca-app-pub-3940256099942544/8691691433",
        "ca-app-pub-3940256099942544/1044960115"
    )

internal fun AdRequest(requestId: String): AdRequest {
    return AdRequest.Builder()
        .apply {
            AdmobAvailabilityAd.getNetworkExtrasBundle()
                .forEach {
                    addNetworkExtrasBundle(it.first.javaClass, it.second)
                }
            if (AdDebug.debug && testVideoId.contains(requestId)) {
                addNetworkExtrasBundle(AdMobAdapter::class.java, Bundle().apply {
                    putString("ft_ctype", "video_app_install")
                })
            }
        }
        .build()
}

internal class SimpleOnPaidEventListener(private val success: (value: AdValue) -> Unit) :
    OnPaidEventListener {
    override fun onPaidEvent(value: AdValue) {
        success.invoke(value)
    }
}