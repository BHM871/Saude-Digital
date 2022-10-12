package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

class SplashDataSourceFactory(private val userSession: Cache<User>) {

    fun createLocalDataSource() : SplashDataSource {
        return SplashLocalDataSource(userSession)
    }

    fun createRemoteDataSource() : SplashDataSource {
        return SplashFireDataSource()
    }

}