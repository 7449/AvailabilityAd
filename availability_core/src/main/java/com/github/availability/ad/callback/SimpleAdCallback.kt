package com.github.availability.ad.callback

import com.github.availability.ad.core.Ad
import com.github.availability.ad.debug.AdLog

class SimpleAdCallback(
    private val callback: () -> OnAdCallback?,
    private val onClicked: () -> Unit
) : OnAdCallback {

    override fun onClicked(ad: Ad) {
        AdLog.i("onClicked")
        onClicked.invoke()
        callback.invoke()?.onClicked(ad)
    }

    override fun onDismissedFullScreenContent(ad: Ad) {
        AdLog.i("onDismissedFullScreenContent")
        callback.invoke()?.onDismissedFullScreenContent(ad)
    }

    override fun onFailedToShowFullScreenContent(ad: Ad, code: Int) {
        AdLog.i("onFailedToShowFullScreenContent")
        callback.invoke()?.onFailedToShowFullScreenContent(ad, code)
    }

    override fun onShowedFullScreenContent(ad: Ad) {
        AdLog.i("onShowedFullScreenContent")
        callback.invoke()?.onShowedFullScreenContent(ad)
    }

    override fun onPaidEvent(ad: Ad, micros: Long, currencyCode: String, precisionType: String) {
        AdLog.i("onPaidEvent")
        callback.invoke()?.onPaidEvent(ad, micros, currencyCode, precisionType)
    }
}