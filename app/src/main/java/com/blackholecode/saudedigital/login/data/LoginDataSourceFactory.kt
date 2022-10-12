package com.blackholecode.saudedigital.login.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

class LoginDataSourceFactory(private val userSession: Cache<User>) {

    fun createLocalDataSource() : LoginDataSource {
        return LoginLocalDataSource(userSession)
    }

    fun createRemoteDataSource() : LoginDataSource {
        return LoginFireDataSource()
    }

}