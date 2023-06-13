package com.github.availability.ad.branch.max.request

import android.content.Context
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdRevenueListener
import com.applovin.mediation.MaxError
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import com.github.availability.ad.compat.AdCompat
import com.github.availability.ad.compat.ResponseIdCompat
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad

internal abstract class MaxAdCompat : AdCompat<MaxAd>() {

    fun revenueListener(context: Context) = MaxAdRevenueListener {
        simpleCallback.onPaidEvent(
            this@MaxAdCompat, it.revenue.toLong(),
            AppLovinSdk.getInstance(context).configuration.countryCode,
            it.revenuePrecision
        )
    }

    private var maxError: MaxError? = null
    private val _responseId = ResponseIdCompat.customAdRequestResponseId()

    override val responseId get() = _responseId
    override val mediationName get() = AppLovinMediationProvider.MAX
    override val sourceName get() = valueOrNull?.networkName ?: maxError?.waterfall?.name.orEmpty()
    override val latencyMillis
        get() = valueOrNull?.requestLatencyMillis
            ?: maxError?.requestLatencyMillis
            ?: super.latencyMillis

    fun completed(result: AdResult, action: Ad.AdCallback, maxError: MaxError? = null) {
        this.maxError = maxError
        completed(result, action)
    }

}