package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.RequestCallback

interface SplashDataSource {
    fun log(callback: RequestCallback<Boolean>) { throw UnsupportedOperationException() }
}