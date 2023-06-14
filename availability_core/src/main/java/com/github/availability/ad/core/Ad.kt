package com.github.availability.ad.core

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import com.github.availability.ad.callback.OnAdCallback
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure

interface Ad {

    interface Callback {
        fun callback(ad: Ad)
    }

    val value: Any?
    val failure: AdFailure?

    val config: AdConfig
    val isReady: Boolean
    val repeatedlyClick: Boolean
    val latencyMillis: Long
    val expireMillis: Long

    fun load(context: Context, callback: Callback)
    fun callback(callback: OnAdCallback)
    fun show(rootView: ViewGroup) {}
    fun show(activity: Activity) {}
    fun show() {}
    fun destroy()

}