package com.github.availability.ad.branch.admob

import android.content.Context
import android.os.Bundle
import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.branch.admob.request.AdManager
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import com.github.availability.ad.core.AdCache
import com.github.availability.ad.debug.AdDebug
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

    override fun init(context: Context, testIds: List<String>) {
        initGoogleAds(
            context.applicationContext,
            testIds
        )
    }

    override fun request(context: Context, config: AdConfig, callback: Ad.AdCallback) {
        create(config)
            .request(context, callback)
    }

    override fun requestOrCache(context: Context, config: AdConfig, callback: Ad.AdCallback) {
        val cache = AdCache.getCache(config.key)
        if (cache == null) {
            request(context, config, callback)
        } else {
            callback.callback(cache)
        }
    }

    override fun create(config: AdConfig): Ad {
        return AdManager.create(config)
    }

    private fun initGoogleAds(context: Context, testIds: List<String> = arrayListOf()) {
        MobileAds.setRequestConfiguration(
            MobileAds.getRequestConfiguration().toBuilder()
                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .build()
        )
        MobileAds.initialize(context) {
            if (AdDebug.debug) {
                MobileAds.setRequestConfiguration(
                    RequestConfiguration.Builder()
                        .setTestDeviceIds(testIds)
                        .build()
                )
            }
            MobileAds.setAppMuted(true)
            MobileAds.getInitializationStatus()?.adapterStatusMap?.forEach { _ ->
            }
        }
    }

}