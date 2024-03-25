package com.github.availability.ad.branch.admob.request

import com.github.availability.ad.core.AdCompat

internal abstract class AdmobAdCompat<AD> : AdCompat<AD>() {

    protected val paidEvent = SimpleOnPaidEventListener {
        simpleCallback.onPaidEvent(
            this,
            it.valueMicros,
            it.currencyCode,
            it.precisionType.toString()
        )
    }

    override val isReady get() = valueOrNull != null

}