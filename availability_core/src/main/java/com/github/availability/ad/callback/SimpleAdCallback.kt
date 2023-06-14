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

    override fun onDismissedFullScreen(ad: Ad) {
        AdLog.i("onDismissedFullScreen")
        callback.invoke()?.onDismissedFullScreen(ad)
    }

    override fun onFailedToShowFullScreen(ad: Ad, code: Int) {
        AdLog.i("onFailedToShowFullScreen")
        callback.invoke()?.onFailedToShowFullScreen(ad, code)
    }

    override fun onShowedFullScreen(ad: Ad) {
        AdLog.i("onShowedFullScreen")
        callback.invoke()?.onShowedFullScreen(ad)
    }

    override fun onPaidEvent(ad: Ad, micros: Long, currencyCode: String, precisionType: String) {
        AdLog.i("onPaidEvent")
        callback.invoke()?.onPaidEvent(ad, micros, currencyCode, precisionType)
    }

    override fun onRewarded(ad: Ad, type: String, amount: Int) {
        super.onRewarded(ad, type, amount)
        AdLog.i("onRewarded")
        callback.invoke()?.onRewarded(ad, type, amount)
    }

}