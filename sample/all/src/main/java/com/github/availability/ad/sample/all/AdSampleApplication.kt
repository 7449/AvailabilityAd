package com.github.availability.ad.sample.all

import android.app.Application
import com.facebook.samples.ads.debugsettings.DebugSettings
import com.github.availability.ad.core.AdLog

class AdSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DebugSettings.initialize(this)
        AdLog.show = true
    }

}