package com.blackholecode.saudedigital.common.util

import android.app.Application

class Application : Application() {

    fun getStringApp(resId: Int) : String {
        return getString(resId)
    }

    fun getStringApp(resId: Int, vararg formatArgs: String) : String {
        return getString(resId, formatArgs)
    }

}