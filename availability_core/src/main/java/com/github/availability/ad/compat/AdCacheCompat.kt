package com.github.availability.ad.compat

import com.github.availability.ad.core.Ad

internal object AdCacheCompat {

    private val adCaches = linkedMapOf<String, Ad?>()

    @Synchronized
    fun checkExpireTimeCache() {
        adCaches.checkExpireTimeCache()
    }

    @Synchronized
    fun getCacheAndRemove(key: String): Ad? {
        adCaches.checkExpireTimeCache()
        return adCaches.remove(key)
    }

    @Synchronized
    fun getCache(key: String): Ad? {
        adCaches.checkExpireTimeCache()
        return adCaches[key]
    }

    @Synchronized
    fun getAllCache(): LinkedHashMap<String, Ad?> {
        return adCaches
    }

    @Synchronized
    fun putAd(key: String, item: Ad) {
        adCaches[key] = item
    }

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