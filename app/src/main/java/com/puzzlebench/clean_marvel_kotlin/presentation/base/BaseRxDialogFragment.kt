package com.puzzlebench.clean_marvel_kotlin.presentation.base

import android.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

open class BaseRxDialogFragment: DialogFragment(){

    var subscriptions= CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions= CompositeDisposable()

    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }
}