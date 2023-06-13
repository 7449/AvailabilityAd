package com.github.availability.ad.branch.admob.config

import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.AdType
import com.google.android.gms.ads.AdSize

data class AdmobAdConfig @JvmOverloads constructor(
    override val id: String,
    override val type: AdType,
    override val key: String = "",
    override val successCache: Boolean = false,
    override val failureCache: Boolean = false,
    override val expireTime: Long = -1,
    val bannerSize: AdSize = AdSize.BANNER,
) : AdConfig
