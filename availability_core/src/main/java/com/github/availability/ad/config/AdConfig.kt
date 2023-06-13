package com.github.availability.ad.config

import com.github.availability.ad.core.AdType

interface AdConfig {
    val id: String
    val type: AdType
    val key: String
    val successCache: Boolean
    val failureCache: Boolean
    val expireTime: Long
}