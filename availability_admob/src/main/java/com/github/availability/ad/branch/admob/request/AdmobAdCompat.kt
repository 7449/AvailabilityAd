package com.github.availability.ad.branch.admob.request

import com.github.availability.ad.compat.AdCompat
import com.github.availability.ad.config.AdFailure
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.google.android.gms.ads.LoadAdError

internal abstract class AdmobAdCompat<AD> : AdCompat<AD>() {

    private var loadAdError: LoadAdError? = null
    private val anyOrNull2 get() = if (anyOrNull is AdFailure) loadAdError else anyOrNull

    protected val paidEvent = SimpleOnPaidEventListener {
        simpleCallback.onPaidEvent(
            this,
            it.valueMicros,
            it.currencyCode,
            it.precisionType.toString()
        )
    }

    override val isReady get() = valueOrNull != null
    override val responseId get() = anyOrNull2?.responseId.orEmpty()
    override val latencyMillis get() = anyOrNull2?.latencyMillis ?: super.latencyMillis
    override val sourceName get() = anyOrNull2?.sourceName.orEmpty()
    override val mediationName get() = anyOrNull2?.mediationGroupName.orEmpty()

    fun completed(result: AdResult, callback: Ad.AdCallback, error: LoadAdError? = null) {
        this.loadAdError = error
        completed(result, callback)
    }

}