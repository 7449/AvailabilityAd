package com.github.availability.ad.sample

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.availability.ad.AvailabilityAd
import com.github.availability.ad.AvailabilityAd.Companion.load
import com.github.availability.ad.AvailabilityAd.Companion.orCache
import com.github.availability.ad.config.AdConfig
import com.github.availability.ad.core.Ad
import java.util.concurrent.TimeUnit

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

    protected val cacheMillis = TimeUnit.HOURS.toMillis(1)
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
        availabilityAd.init(applicationContext)
        loadNative()
        loadInterstitialView()
        loadInterstitialVideoView()
        loadAppOpenView()
        loadBannerView()
        loadRewardedVideo()
        loadRewardedInterstitial()
    }

    private fun loadStart() {
        progress.isVisible = true
        resultView.text = ""
        nativeGroup.removeAllViews()
        currentAd?.destroy()
        currentAd = null
    }

    private fun loadEnd(ad: Ad) {
        resultView.text = ad.toString()
        progress.isVisible = false
        currentAd = ad
    }

    private fun loadRewardedVideo() {
        rewardedVideo.setOnClickListener {
            loadStart()
            availabilityAd.load(this, rewardedVideoConfig) {
                loadEnd(it)
                it.orCache()?.show(this)
            }
        }
    }

    private fun loadRewardedInterstitial() {
        rewardedInterstitial.setOnClickListener {
            loadStart()
            availabilityAd.load(this, rewardedInterstitialConfig) {
                loadEnd(it)
                it.orCache()?.show(this)
            }
        }
    }

    private fun loadAppOpenView() {
        appOpenView.setOnClickListener {
            loadStart()
            availabilityAd.load(this, appOpenConfig) {
                loadEnd(it)
                it.orCache()?.show(this)
            }
        }
    }

    private fun loadInterstitialVideoView() {
        interstitialVideoView.setOnClickListener {
            loadStart()
            availabilityAd.load(this, interstitialVideoConfig) {
                loadEnd(it)
                it.orCache()?.show(this)
            }
        }
    }

    private fun loadInterstitialView() {
        interstitialView.setOnClickListener {
            loadStart()
            availabilityAd.load(this, interstitialConfig) {
                loadEnd(it)
                it.orCache()?.show(this)
            }
        }
    }

    private fun loadNative() {
        nativeView.setOnClickListener {
            loadStart()
            availabilityAd.load(this, nativeConfig) {
                loadEnd(it)
                it.orCache()?.show(nativeGroup)
            }
        }
    }

    private fun loadBannerView() {
        bannerView.setOnClickListener {
            loadStart()
            availabilityAd.load(this, bannerConfig) {
                loadEnd(it)
                it.orCache()?.show(nativeGroup)
            }
        }
    }

}