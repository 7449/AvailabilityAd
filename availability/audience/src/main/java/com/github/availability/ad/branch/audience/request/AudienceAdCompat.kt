package com.github.availability.ad.branch.audience.request

import com.facebook.ads.Ad
import com.github.availability.ad.core.AdCompat

internal abstract class AudienceAdCompat<AD> : AdCompat<AD>() {

    override val isReady get() = if (valueOrNull is Ad) !(valueOrNull as Ad).isAdInvalidated else valueOrNull != null

}