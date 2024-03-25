package com.github.availability.ad.core

import android.util.Log

object AdLog {

    var show = false

    fun e(msg: String) {
        if (show) {
            Log.e("AvailabilityAd", msg)
        }
    }

    fun i(msg: String) {
        if (show) {
            Log.i("AvailabilityAd", msg)
        }
    }

    fun w(msg: String) {
        if (show) {
            Log.w("AvailabilityAd", msg)
        }
    }

}