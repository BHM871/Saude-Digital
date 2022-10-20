package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

interface SplashDataSource {

    fun log(callback: RequestCallback<Boolean>) { throw UnsupportedOperationException() }

}