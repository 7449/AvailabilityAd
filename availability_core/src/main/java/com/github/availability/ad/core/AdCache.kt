package com.github.availability.ad.core

import com.github.availability.ad.compat.AdCacheCompat
import com.github.availability.ad.debug.AdLog

object AdCache {

    @JvmStatic
    fun checkExpireTimeCache() {
        AdLog.i("checkExpireTimeCache")
        AdCacheCompat.checkExpireTimeCache()
    }

    @JvmStatic
    fun getCacheAndRemove(key: String): Ad? {
        AdLog.i("getCacheAndRemove[$key]")
        return AdCacheCompat.getCacheAndRemove(key)
    }

    @JvmStatic
    fun getCache(key: String): Ad? {
        AdLog.i("getCache[$key]")
        return AdCacheCompat.getCache(key)
    }

    @JvmStatic
    fun getAllCache(): LinkedHashMap<String, Ad?> {
        AdLog.i("getAllCache")
        return AdCacheCompat.getAllCache()
    }

}