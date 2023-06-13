@file:Suppress("UNCHECKED_CAST")

package com.github.availability.ad.config

import androidx.annotation.CallSuper
import com.github.availability.ad.debug.AdLog

sealed class AdResult {

    data class Success(private var value: Any?) : AdResult() {
        val immutable get() = value
        override fun destroy() {
            super.destroy()
            value = null
        }
    }

    data class Failure(private var value: AdFailure?) : AdResult() {
        val immutable get() = value
        override fun destroy() {
            super.destroy()
            value = null
        }
    }

    fun <T> valueOrNull(): T? {
        return if (this is Success) immutable as T else null
    }

    fun failureOrNull(): AdFailure? {
        return if (this is Failure) immutable as AdFailure else null
    }

    fun anyOrNull(): Any? {
        return valueOrNull() ?: failureOrNull()
    }

    @CallSuper
    open fun destroy() {
        AdLog.i("AdValueDestroy")
    }

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[$immutable]"
            is Failure -> "Failure[$immutable]"
        }
    }

}
