package com.github.availability.ad.sample.max

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.applovin.sdk.AppLovinSdk

class AdMaxSettingSampleActivity : AppCompatActivity(R.layout.activity_sample_max) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.ad_max).setOnClickListener {
            startActivity(Intent(this, AdMaxSampleActivity::class.java))
        }
        findViewById<TextView>(R.id.max_setting).setOnClickListener {
            AppLovinSdk.getInstance(this).showMediationDebugger()
        }
    }

}