package com.github.availability.ad.callback

import com.github.availability.ad.core.Ad
import com.github.availability.ad.debug.AdLog

class SimpleRewardedCallback(
    private val callback: () -> OnRewardedCallback?,
) : OnRewardedCallback {

    override fun rewarded(ad: Ad, type: String, amount: Int) {
        AdLog.i("rewarded")
        callback.invoke()?.rewarded(ad, type, amount)
    }

}