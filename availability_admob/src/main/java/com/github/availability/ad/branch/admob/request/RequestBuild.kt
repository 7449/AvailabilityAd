package com.github.availability.ad.branch.admob.request

import android.os.Bundle
import com.github.availability.ad.debug.AdDebug
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnPaidEventListener
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd

internal const val mediation_group_name = "mediation_group_name"

internal val Any.sourceName
    get() = when (this) {
        is LoadAdError -> responseInfo?.loadedAdapterResponseInfo?.adSourceName
        is AdView -> responseInfo?.loadedAdapterResponseInfo?.adSourceName
        is NativeAd -> responseInfo?.loadedAdapterResponseInfo?.adSourceName
        is AppOpenAd -> responseInfo.loadedAdapterResponseInfo?.adSourceName
        is InterstitialAd -> responseInfo.loadedAdapterResponseInfo?.adSourceName
        is RewardedAd -> responseInfo.loadedAdapterResponseInfo?.adSourceName
        is RewardedInterstitialAd -> responseInfo.loadedAdapterResponseInfo?.adSourceName
        else -> ""
    }

internal val Any.responseId
    get() = when (this) {
        is LoadAdError -> responseInfo?.responseId
        is AdView -> responseInfo?.responseId
        is NativeAd -> responseInfo?.responseId
        is AppOpenAd -> responseInfo.responseId
        is InterstitialAd -> responseInfo.responseId
        is RewardedAd -> responseInfo.responseId
        is RewardedInterstitialAd -> responseInfo.responseId
        else -> ""
    }

internal val Any.mediationGroupName
    get() = when (this) {
        is LoadAdError -> responseInfo?.responseExtras?.getString(mediation_group_name)
        is AdView -> responseInfo?.responseExtras?.getString(mediation_group_name)
        is NativeAd -> responseInfo?.responseExtras?.getString(mediation_group_name)
        is AppOpenAd -> responseInfo.responseExtras.getString(mediation_group_name)
        is InterstitialAd -> responseInfo.responseExtras.getString(mediation_group_name)
        is RewardedAd -> responseInfo.responseExtras.getString(mediation_group_name)
        is RewardedInterstitialAd -> responseInfo.responseExtras.getString(mediation_group_name)
        else -> ""
    }

internal val Any.latencyMillis
    get() = when (this) {
        is LoadAdError -> responseInfo?.loadedAdapterResponseInfo?.latencyMillis
        is AdView -> responseInfo?.loadedAdapterResponseInfo?.latencyMillis
        is NativeAd -> responseInfo?.loadedAdapterResponseInfo?.latencyMillis
        is AppOpenAd -> responseInfo.loadedAdapterResponseInfo?.latencyMillis
        is InterstitialAd -> responseInfo.loadedAdapterResponseInfo?.latencyMillis
        is RewardedAd -> responseInfo.loadedAdapterResponseInfo?.latencyMillis
        is RewardedInterstitialAd -> responseInfo.loadedAdapterResponseInfo?.latencyMillis
        else -> null
    }

internal val testVideoId =
    arrayListOf(
        "ca-app-pub-3940256099942544/8691691433",
        "ca-app-pub-3940256099942544/1044960115"
    )

internal val testId =
    arrayListOf(
        "ca-app-pub-3940256099942544/3419835294",
        "ca-app-pub-3940256099942544/1033173712",
        "ca-app-pub-3940256099942544/2247696110"
    )

internal fun AdRequest(requestId: String): AdRequest {
    return AdRequest.Builder()
        .apply {
            com.github.availability.ad.branch.admob.AdmobAvailabilityAd.getNetworkExtrasBundle()
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