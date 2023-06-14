package com.github.availability.ad.core

import com.github.availability.ad.debug.AdLog

object AdCache {

    private val adCaches = linkedMapOf<String, Ad?>()

    @JvmStatic
    @Synchronized
    fun checkExpireTimeCache() {
        AdLog.i("checkExpireTimeCache")
        adCaches.checkExpireTimeCache()
    }

    @JvmStatic
    @Synchronized
    fun getCacheAndRemove(key: String): Ad? {
        AdLog.i("getCacheAndRemove")
        adCaches.checkExpireTimeCache()
        return adCaches.remove(key)
    }

    @JvmStatic
    @Synchronized
    fun getCache(key: String): Ad? {
        AdLog.i("getCache")
        adCaches.checkExpireTimeCache()
        return adCaches[key]
    }

    @JvmStatic
    @Synchronized
    fun getAllCache(): LinkedHashMap<String, Ad?> {
        AdLog.i("getAllCache")
        adCaches.checkExpireTimeCache()
        return adCaches
    }

    @JvmStatic
    @Synchronized
    fun containsKey(key: String): Boolean {
        AdLog.i("containsKey")
        adCaches.checkExpireTimeCache()
        return adCaches.containsKey(key)
    }

    @JvmStatic
    @Synchronized
    fun putAd(key: String, item: Ad) {
        AdLog.i("putAd")
        if (containsKey(key)) {
            getCacheAndRemove(key)?.destroy()
        }
        adCaches[key] = item
    }

    @JvmStatic
    @Synchronized
    private fun LinkedHashMap<String, Ad?>.checkExpireTimeCache() {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val next = iterator.next().value ?: return let { iterator.remove() }
            if (next.expireMillis < System.currentTimeMillis()) {
                next.destroy()
                iterator.remove()
            }
        }
    }

}