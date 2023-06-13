package com.github.availability.ad.debug

import android.util.Log

object AdLog {

    fun e(msg: String) {
        if (AdDebug.debug) {
            Log.e("AvailabilityAd", msg)
        }
    }

    fun i(msg: String) {
        if (AdDebug.debug) {
            Log.i("AvailabilityAd", msg)
        }
    }

    fun w(msg: String) {
        if (AdDebug.debug) {
            Log.w("AvailabilityAd", msg)
        }
    }

}