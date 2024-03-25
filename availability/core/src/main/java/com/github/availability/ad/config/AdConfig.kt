package com.github.availability.ad.config

import com.github.availability.ad.core.AdType

enum class AdCacheSaveType {
    SUCCESS,
    ALL
}

interface AdCacheConfig {
    val key: String
    val type: AdCacheSaveType
    val expireTime: Long
}

interface AdConfig {
    val id: String
    val type: AdType
    val cache: AdCacheConfig?
}

fun AdConfig.cacheKeyNotEmpty(): Boolean {
    return cache?.key.orEmpty().isNotEmpty()
}

fun AdConfig.cacheKey(): String {
    return cache?.key.orEmpty()
}

fun AdConfig.cacheType(): AdCacheSaveType? {
    return cache?.type
}

fun AdConfig.expireTime(): Long {
    return cache?.expireTime ?: -1
}

data class SimpleAdCacheConfig(
    override val key: String,
    override val type: AdCacheSaveType,
    override val expireTime: Long,
) : AdCacheConfig {

    companion object {

        fun successful(key: String, expireTime: Long): SimpleAdCacheConfig {
            return SimpleAdCacheConfig(key, AdCacheSaveType.SUCCESS, expireTime)
        }

        fun all(key: String, expireTime: Long): SimpleAdCacheConfig {
            return SimpleAdCacheConfig(key, AdCacheSaveType.ALL, expireTime)
        }

    }

}

data class SimpleAdConfig(
    override val id: String,
    override val type: AdType,
    override val cache: AdCacheConfig? = null,
) : AdConfig {

    companion object {

        fun noCache(id: String, type: AdType): SimpleAdConfig {
            return SimpleAdConfig(id, type)
        }

        fun successful(id: String, type: AdType, expireTime: Long): SimpleAdConfig {
            return SimpleAdConfig(id, type, SimpleAdCacheConfig.successful(id, expireTime))
        }

        fun all(id: String, type: AdType, expireTime: Long): SimpleAdConfig {
            return SimpleAdConfig(id, type, SimpleAdCacheConfig.all(id, expireTime))
        }

    }

}