package com.github.availability.ad.callback

import com.github.availability.ad.core.Ad

interface OnRewardedCallback {
    fun rewarded(ad: Ad, type: String, amount: Int)
}