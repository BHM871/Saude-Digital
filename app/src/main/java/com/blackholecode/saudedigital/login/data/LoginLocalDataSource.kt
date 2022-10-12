package com.blackholecode.saudedigital.login.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User
import java.util.*

class LoginLocalDataSource(private val userSession: Cache<User>) : LoginDataSource {

    override fun putCache(data: User) {
        userSession.put(data)
    }

}