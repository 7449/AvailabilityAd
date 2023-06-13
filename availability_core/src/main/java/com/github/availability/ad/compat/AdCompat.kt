package com.github.availability.ad.compat

import androidx.annotation.CallSuper
import com.github.availability.ad.callback.OnAdCallback
import com.github.availability.ad.callback.SimpleAdCallback
import com.github.availability.ad.compat.StringBuilderCompat.formatString
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.core.Ad
import com.github.availability.ad.debug.AdLog

abstract class AdCompat<AD> : Ad {

    val valueOrNull: AD? get() = _result?.valueOrNull()

    private val _requestFirstMillis: Long = System.currentTimeMillis()
    private var _requestLastMillis: Long = _requestFirstMillis
    private var _result: AdResult? = null
    private var _callback: OnAdCallback? = null
    private var _adClickCount: Int = 0

    protected val simpleCallback =
        SimpleAdCallback({ _callback }) { _adClickCount++ }

    override val value: Any? get() = valueOrNull
    override val failure get() = _result?.failureOrNull()
    override val repeatedlyClick get() = _adClickCount > 1
    override val latencyMillis get() = _requestLastMillis - _requestFirstMillis
    override val expireMillis get() = _requestLastMillis + config.expireTime

    override fun callback(callback: OnAdCallback) {
        this._callback = callback
    }

    override fun toString(): String {
        return formatString()
    }

    @CallSuper
    override fun destroy() {
        _callback = null
        _result?.destroy()
        _result = null
    }

    fun completed(result: AdResult, action: Ad.AdCallback) {
        AdLog.i("Ad Load Completed Result [$result]")
        _result = result
        _requestLastMillis = System.currentTimeMillis()
        if (result is AdResult.Failure && config.failureCache && config.key.isNotBlank()) {
            AdCacheCompat.putAd(config.key, this)
        }
        if (result is AdResult.Success && config.successCache && config.key.isNotBlank()) {
            AdCacheCompat.putAd(config.key, this)
        }
        action.callback(this)
    }

}