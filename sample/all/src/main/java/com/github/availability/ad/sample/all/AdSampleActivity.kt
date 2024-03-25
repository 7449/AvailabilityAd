package com.github.availability.ad.sample.all

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.applovin.sdk.AppLovinSdk
import com.facebook.samples.ads.debugsettings.DebugSettingsActivity

class AdSampleActivity : AppCompatActivity(R.layout.activity_sample_all) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.ad_max).setOnClickListener {
            startActivity(Intent(this, AdMaxSampleActivity::class.java))
        }
        findViewById<TextView>(R.id.max_setting).setOnClickListener {
            AppLovinSdk.getInstance(this).showMediationDebugger()
        }
        findViewById<TextView>(R.id.ad_mob).setOnClickListener {
            startActivity(Intent(this, AdAdmobSampleActivity::class.java))
        }
        findViewById<TextView>(R.id.audience).setOnClickListener {
            startActivity(Intent(this, AdAudienceSampleActivity::class.java))
        }
        findViewById<TextView>(R.id.audience_setting).setOnClickListener {
            startActivity(Intent(this, DebugSettingsActivity::class.java))
        }
    }

}