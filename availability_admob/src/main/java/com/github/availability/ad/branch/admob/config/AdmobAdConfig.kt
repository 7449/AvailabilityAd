package com.github.availability.ad.branch.admob.config

import com.github.availability.ad.config.AdCacheConfig
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.AdType
import com.google.android.gms.ads.AdSize

data class AdmobAdConfig @JvmOverloads constructor(
    override val id: String,
    override val type: AdType,
    val bannerSize: AdSize = AdSize.BANNER,
    override val cache: AdCacheConfig? = null,
) : AdConfig
