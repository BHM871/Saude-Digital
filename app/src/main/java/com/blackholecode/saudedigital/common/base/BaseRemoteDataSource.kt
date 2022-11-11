package com.blackholecode.saudedigital.common.base

import android.app.Activity
import android.app.Application
import android.os.Handler
import android.os.Looper
import com.blackholecode.saudedigital.R

abstract class BaseRemoteDataSource(private val activity: Activity) {

    protected lateinit var app: Application
    protected var isComplete = false

    fun <T> timeOut(
        callback: RequestCallback<T>
    ) {

        app = activity.application

        Handler(Looper.getMainLooper()).postDelayed({

            if (!isComplete) {
                app.let { callback.onFailure(it.getString(R.string.error_time_out)) }
                callback.onComplete()
            }
        }, 20_000)

    }

}