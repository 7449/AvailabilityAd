package com.github.availability.ad.config

import com.github.availability.ad.core.AdType

data class SimpleAdConfig @JvmOverloads constructor(
    override val id: String,
    override val type: AdType,
    override val key: String = "",
    override val successCache: Boolean = false,
    override val failureCache: Boolean = false,
    override val expireTime: Long = -1
) : AdConfig {

    companion object {

        @JvmStatic
        fun success(id: String, type: AdType, key: String, expireTime: Long): SimpleAdConfig {
            return SimpleAdConfig(
                id, type, key,
                successCache = true,
                failureCache = false,
                expireTime = expireTime
            )
        }

    }

}