package com.blackholecode.saudedigital.login.data

import com.blackholecode.saudedigital.common.base.RequestCallback

class LoginRepository(private val data: LoginDataSource) {

    fun login(email: String, password: String, callback: RequestCallback<Boolean>) {
        data.login(email, password, callback)
    }

}