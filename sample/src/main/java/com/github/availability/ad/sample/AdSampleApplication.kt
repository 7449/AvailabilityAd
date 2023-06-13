package com.github.availability.ad.sample

import android.app.Application
import com.facebook.samples.ads.debugsettings.DebugSettings
import com.github.availability.ad.debug.AdDebug

class AdSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DebugSettings.initialize(this)
        AdDebug.debug = true
    }

}