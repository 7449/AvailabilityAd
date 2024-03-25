package com.github.availability.ad.core

sealed class AdType(val value: String) {
    data object Interstitial : AdType("interstitial")
    data object Native : AdType("native_advanced")
    data object AppOpen : AdType("app_open")
    data object NativeBanner : AdType("native_banner")
    data object RewardedVideo : AdType("rewarded_video")
    data object RewardedInterstitial : AdType("rewarded_interstitial")
}

