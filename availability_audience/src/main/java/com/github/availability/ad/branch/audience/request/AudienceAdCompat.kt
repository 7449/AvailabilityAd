package com.github.availability.ad.branch.audience.request

import com.facebook.ads.Ad
import com.github.availability.ad.compat.AdCompat
import com.github.availability.ad.compat.ResponseIdCompat

internal abstract class AudienceAdCompat<AD> : AdCompat<AD>() {

    private val _responseId = ResponseIdCompat.customAdRequestResponseId()

    override val isReady get() = if (valueOrNull is Ad) !(valueOrNull as Ad).isAdInvalidated else valueOrNull != null
    override val responseId get() = _responseId
    override val sourceName get() = ""
    override val mediationName get() = ""

}