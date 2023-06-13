package com.github.availability.ad.core

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import com.github.availability.ad.callback.OnAdCallback
import com.github.availability.ad.callback.OnRewardedCallback
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.config.AdFailure

interface Ad {

    interface AdCallback {
        fun callback(ad: Ad)
    }

    val config: AdConfig

    val isReady: Boolean

    /** 是否被点击过 */
    val repeatedlyClick: Boolean

    /** response info */
    val sourceName: String
    val responseId: String
    val mediationName: String
    val latencyMillis: Long
    val expireMillis: Long

    val failure: AdFailure?

    fun request(context: Context, callback: AdCallback)
    fun callback(callback: OnAdCallback)
    fun rewardedCallback(callback: OnRewardedCallback)
    fun showNative(rootView: ViewGroup) {}
    fun showFullScreen(activity: Activity) {}
    fun destroy()

}