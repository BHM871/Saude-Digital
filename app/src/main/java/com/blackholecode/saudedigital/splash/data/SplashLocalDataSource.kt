package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User
import java.util.*

class SplashLocalDataSource(private val userSession: Cache<User>) : SplashDataSource {

    override fun putCache(data: User) {
        userSession.put(data)
    }

}