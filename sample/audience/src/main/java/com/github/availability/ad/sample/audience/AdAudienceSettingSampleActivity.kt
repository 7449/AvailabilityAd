package com.github.availability.ad.sample.audience

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.samples.ads.debugsettings.DebugSettingsActivity

class AdAudienceSettingSampleActivity : AppCompatActivity(R.layout.activity_sample_audience) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.audience).setOnClickListener {
            startActivity(Intent(this, AdAudienceSampleActivity::class.java))
        }
        findViewById<TextView>(R.id.audience_setting).setOnClickListener {
            startActivity(Intent(this, DebugSettingsActivity::class.java))
        }
    }

}