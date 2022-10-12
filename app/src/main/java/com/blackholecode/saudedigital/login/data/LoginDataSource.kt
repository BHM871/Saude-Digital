package com.blackholecode.saudedigital.login.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

interface LoginDataSource {

    fun login(email: String, password: String, callback: RequestCallback<User>) { throw UnsupportedOperationException() }

    fun putCache(data: User) { throw UnsupportedOperationException() }

}