package com.blackholecode.saudedigital.common.base

import android.app.Activity
import android.app.Application
import android.os.Handler
import android.os.Looper
import com.blackholecode.saudedigital.R

abstract class BaseRemoteDataSource(private val activity: Activity) {

    protected val app: Application = activity.application
    protected var isComplete = false

    fun <T> timeOut(
        callback: RequestCallback<T>
    ) {

        Handler(Looper.getMainLooper()).postDelayed({

            if (!isComplete) {
                callback.onFailure(activity.getString(R.string.error_time_out))
                callback.onComplete()
            }
        }, 16_000)

    }

}