package com.github.availability.ad.branch.max.request

import android.content.Context
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdRevenueListener
import com.applovin.sdk.AppLovinSdk
import com.github.availability.ad.compat.AdCompat

internal abstract class MaxAdCompat : AdCompat<MaxAd>() {

    fun revenueListener(context: Context) = MaxAdRevenueListener {
        simpleCallback.onPaidEvent(
            this@MaxAdCompat, it.revenue.toLong(),
            AppLovinSdk.getInstance(context).configuration.countryCode,
            it.revenuePrecision
        )
    }

}