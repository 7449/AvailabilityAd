package com.github.availability.ad.core

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.github.availability.ad.debug.AdLog

open class AdLifecycleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {

        fun Ad.createLifecycleView(context: Context): AdLifecycleView {
            return AdLifecycleView(context).registerAd(this)
        }

    }

    private var ad: Ad? = null

    fun registerAd(ad: Ad) = apply {
        AdLog.i("AdLifecycleView Register $ad")
        this.ad?.destroy()
        this.ad = null
        this.ad = ad
    }

    fun addViews(view: View) = apply {
        addView(view)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        AdLog.i("AdLifecycleView Destroy $ad")
        ad?.destroy()
    }

}