package com.github.availability.ad.callback

import com.github.availability.ad.core.Ad

interface OnAdCallback {
    fun onClicked(ad: Ad)
    fun onDismissedFullScreen(ad: Ad)
    fun onFailedToShowFullScreen(ad: Ad, code: Int)
    fun onShowedFullScreen(ad: Ad)
    fun onPaidEvent(ad: Ad, micros: Long, currencyCode: String, precisionType: String)
    fun onRewarded(ad: Ad, type: String, amount: Int) {} // ignore
}