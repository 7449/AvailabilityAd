package com.github.availability.ad.core

import android.content.Context
import androidx.annotation.CallSuper
import com.github.availability.ad.callback.OnAdCallback
import com.github.availability.ad.callback.SimpleAdCallback
import com.github.availability.ad.config.AdCacheSaveType
import com.github.availability.ad.config.AdResult
import com.github.availability.ad.config.cacheKey
import com.github.availability.ad.config.cacheKeyNotEmpty
import com.github.availability.ad.config.cacheType
import com.github.availability.ad.config.expireTime
import org.json.JSONObject

abstract class AdCompat<AD> : Ad {

    val valueOrNull: AD? get() = _result?.valueOrNull()

    private var _loadFirstMillis: Long = System.currentTimeMillis()
    private var _loadLastMillis: Long = _loadFirstMillis
    private var _result: AdResult? = null
    private var _callback: OnAdCallback? = null
    private var _adClickCount: Int = 0

    protected val simpleCallback = SimpleAdCallback({ _callback }) { _adClickCount++ }

    override val value: Any? get() = valueOrNull
    override val failure get() = _result?.failureOrNull()
    override val repeatedlyClick get() = _adClickCount > 1
    override val latencyMillis get() = _loadLastMillis - _loadFirstMillis
    override val expireMillis get() = _loadLastMillis + config.expireTime()

    override fun callback(callback: OnAdCallback) {
        this._callback = callback
    }

    @CallSuper
    override fun load(context: Context, callback: Ad.Callback) {
        _loadFirstMillis = System.currentTimeMillis()
    }

    @CallSuper
    override fun destroy() {
        _callback = null
        _result?.destroy()
        _result = null
    }

    @CallSuper
    override fun toString(): String {
        val jb = JSONObject()
        jb.put("ad_id", config.id)
        jb.put("cache_key", config.cache?.key.toString())
        jb.put("value", value.toString())
        jb.put("failure", failure.toString())
        jb.put("latencyMillis", latencyMillis)
        jb.put("expireMillis", expireMillis)
        return jb.toString()
    }

    fun completed(result: AdResult, callback: Ad.Callback) {
        AdLog.i("Ad Load Completed Result [$result]")
        _result = result
        _loadLastMillis = System.currentTimeMillis()
        when {
            config.cacheType() == AdCacheSaveType.SUCCESS && config.cacheKeyNotEmpty() && result is AdResult.Success ->
                AdCache.putAd(config.cacheKey(), this)

            config.cacheType() == AdCacheSaveType.ALL && config.cacheKeyNotEmpty() ->
                AdCache.putAd(config.cacheKey(), this)
        }
        callback.callback(this)
    }

}