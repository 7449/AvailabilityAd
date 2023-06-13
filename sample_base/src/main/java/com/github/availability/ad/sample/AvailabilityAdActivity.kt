package com.github.availability.ad.sample

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.AvailabilityAd.Companion.orCache
import com.github.availability.ad.AvailabilityAd.Companion.load
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad

abstract class AvailabilityAdActivity : AppCompatActivity(R.layout.activity_availability_ad) {

    private val progress by lazy { findViewById<ProgressBar>(R.id.progress) }
    private val resultView by lazy { findViewById<TextView>(R.id.result) }
    private val nativeView by lazy { findViewById<TextView>(R.id.natives) }
    private val bannerView by lazy { findViewById<TextView>(R.id.native_banner) }
    private val interstitialView by lazy { findViewById<TextView>(R.id.interstitial) }
    private val interstitialVideoView by lazy { findViewById<TextView>(R.id.interstitial_video) }
    private val appOpenView by lazy { findViewById<TextView>(R.id.app_open) }
    private val nativeGroup by lazy { findViewById<FrameLayout>(R.id.native_view) }
    private val rewardedVideo by lazy { findViewById<TextView>(R.id.rewarded_video) }
    private val rewardedInterstitial by lazy { findViewById<TextView>(R.id.rewarded_interstitial) }

    private var currentAd: Ad? = null

    protected abstract val availabilityAd: AvailabilityAd

    protected abstract val bannerConfig: AdConfig
    protected abstract val nativeConfig: AdConfig
    protected abstract val appOpenConfig: AdConfig
    protected abstract val interstitialConfig: AdConfig
    protected abstract val interstitialVideoConfig: AdConfig
    protected abstract val rewardedVideoConfig: AdConfig
    protected abstract val rewardedInterstitialConfig: AdConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        availabilityAd.init(applicationContext, arrayListOf())
        requestNative()
        requestInterstitialView()
        requestInterstitialVideoView()
        requestAppOpenView()
        requestBannerView()
        requestRewardedVideo()
        requestRewardedInterstitial()
    }

    private fun requestStart() {
        progress.isVisible = true
        resultView.text = ""
        nativeGroup.removeAllViews()
        currentAd?.destroy()
        currentAd = null
    }

    private fun requestEnd(ad: Ad) {
        resultView.text = ad.toString()
        progress.isVisible = false
        currentAd = ad
    }

    private fun requestRewardedVideo() {
        rewardedVideo.setOnClickListener {
            requestStart()
            availabilityAd.load(this, rewardedVideoConfig) {
                requestEnd(it)
                it.orCache()?.showFullScreen(this)
            }
        }
    }

    private fun requestRewardedInterstitial() {
        rewardedInterstitial.setOnClickListener {
            requestStart()
            availabilityAd.load(this, rewardedInterstitialConfig) {
                requestEnd(it)
                it.orCache()?.showFullScreen(this)
            }
        }
    }

    private fun requestAppOpenView() {
        appOpenView.setOnClickListener {
            requestStart()
            availabilityAd.load(this, appOpenConfig) {
                requestEnd(it)
                it.orCache()?.showFullScreen(this)
            }
        }
    }

    private fun requestInterstitialVideoView() {
        interstitialVideoView.setOnClickListener {
            requestStart()
            availabilityAd.load(this, interstitialVideoConfig) {
                requestEnd(it)
                it.orCache()?.showFullScreen(this)
            }
        }
    }

    private fun requestInterstitialView() {
        interstitialView.setOnClickListener {
            requestStart()
            availabilityAd.load(this, interstitialConfig) {
                requestEnd(it)
                it.orCache()?.showFullScreen(this)
            }
        }
    }

    private fun requestNative() {
        nativeView.setOnClickListener {
            requestStart()
            availabilityAd.load(this, nativeConfig) {
                requestEnd(it)
                it.orCache()?.showNative(nativeGroup)
            }
        }
    }

    private fun requestBannerView() {
        bannerView.setOnClickListener {
            requestStart()
            availabilityAd.load(this, bannerConfig) {
                requestEnd(it)
                it.orCache()?.showNative(nativeGroup)
            }
        }
    }

}