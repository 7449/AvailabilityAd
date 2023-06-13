package com.github.availability.ad.callback

import com.github.availability.ad.core.Ad

interface OnAdCallback {
    fun onClicked(ad: Ad)
    fun onDismissedFullScreenContent(ad: Ad)
    fun onFailedToShowFullScreenContent(ad: Ad, code: Int)
    fun onShowedFullScreenContent(ad: Ad)
    fun onPaidEvent(ad: Ad, micros: Long, currencyCode: String, precisionType: String)
    fun rewarded(ad: Ad, type: String, amount: Int) {} // ignore
}