package com.github.availability.ad.branch.admob

import android.content.Context
import android.os.Bundle
import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.admob.request.AdManager
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.cacheKey
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdCache
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.mediation.MediationExtrasReceiver

object AdmobAvailabilityAd : AvailabilityAd {

    private val networkExtrasBundles: MutableList<Pair<MediationExtrasReceiver, Bundle>> =
        arrayListOf()

    fun addNetworkExtrasBundle(extras: MutableList<Pair<MediationExtrasReceiver, Bundle>>) {
        networkExtrasBundles.addAll(extras)
    }

    fun getNetworkExtrasBundle(): MutableList<Pair<MediationExtrasReceiver, Bundle>> {
        return networkExtrasBundles
    }

    override fun init(context: Context) {
        initGoogleAds(context)
    }

    override fun load(context: Context, config: AdConfig, callback: Ad.Callback) {
        create(config).load(context, callback)
    }

    override fun loadOrCache(context: Context, config: AdConfig, callback: Ad.Callback) {
        val cache = AdCache.getCacheAndRemove(config.cacheKey())
        if (cache == null) {
            load(context, config, callback)
        } else {
            callback.callback(cache)
        }
    }

    override fun create(config: AdConfig): Ad {
        return AdManager.create(config)
    }

    private fun initGoogleAds(context: Context) {
        MobileAds.setRequestConfiguration(
            MobileAds.getRequestConfiguration().toBuilder()
                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .build()
        )
        MobileAds.initialize(context) {
            MobileAds.setAppMuted(true)
            MobileAds.getInitializationStatus()?.adapterStatusMap?.forEach { _ ->
            }
        }
    }

}