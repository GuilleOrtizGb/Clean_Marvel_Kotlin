package com.puzzlebench.clean_marvel_kotlin.Utils

import android.os.SystemClock
import android.util.Log
import android.view.View

class DebouncedOnClickListener (private val clickListener: View.OnClickListener,
                                           private val debounceInterval: Long = DEBOUNCE_INTERVAL_DEFAULT) : View.OnClickListener {
    private var lastClickTime: Long = 0

    companion object {
        private val DEBOUNCE_INTERVAL_DEFAULT: Long = 500
    }

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < debounceInterval) {
            Log.v("Debounced","Click Debounced")
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        clickListener.onClick(v)
    }
}