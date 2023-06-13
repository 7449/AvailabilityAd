package com.github.availability.ad.core

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import com.github.availability.ad.callback.OnAdCallback
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure

interface Ad {

    interface AdCallback {
        fun callback(ad: Ad)
    }

    val config: AdConfig

    val isReady: Boolean

    val repeatedlyClick: Boolean

    val latencyMillis: Long
    val expireMillis: Long

    val value: Any?
    val failure: AdFailure?

    fun request(context: Context, callback: AdCallback)
    fun callback(callback: OnAdCallback)
    fun showNative(rootView: ViewGroup) {}
    fun showFullScreen(activity: Activity) {}
    fun destroy()

}