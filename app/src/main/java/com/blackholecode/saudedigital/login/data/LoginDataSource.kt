package com.blackholecode.saudedigital.login.data

import com.blackholecode.saudedigital.common.base.RequestCallback

interface LoginDataSource {

    fun login(email: String, password: String, callback: RequestCallback<Boolean>)

}