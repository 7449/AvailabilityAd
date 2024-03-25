package com.github.availability.ad.sample.audience

import android.app.Application
import com.facebook.samples.ads.debugsettings.DebugSettings

class AdAudienceSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DebugSettings.initialize(this)
    }

}