package com.github.availability.ad.core

object AdCache {

    private val adCaches = linkedMapOf<String, Ad?>()

    @Synchronized
    fun checkExpireTimeCache() {
        AdLog.i("checkExpireTimeCache")
        adCaches.checkExpireTimeCache()
    }

    @Synchronized
    fun getCacheAndRemove(key: String): Ad? {
        AdLog.i("getCacheAndRemove")
        adCaches.checkExpireTimeCache()
        return adCaches.remove(key)
    }

    @Deprecated("@see getCacheAndRemove(key)")
    @Synchronized
    fun getCache(key: String): Ad? {
        AdLog.i("getCache")
        adCaches.checkExpireTimeCache()
        return adCaches[key]
    }

    @Synchronized
    fun getAllCache(): LinkedHashMap<String, Ad?> {
        AdLog.i("getAllCache")
        adCaches.checkExpireTimeCache()
        return adCaches
    }

    @Synchronized
    fun containsKey(key: String): Boolean {
        AdLog.i("containsKey")
        adCaches.checkExpireTimeCache()
        return adCaches.containsKey(key)
    }

    @Synchronized
    fun putAd(key: String, item: Ad) {
        AdLog.i("putAd")
        if (containsKey(key)) {
            getCacheAndRemove(key)?.destroy()
        }
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