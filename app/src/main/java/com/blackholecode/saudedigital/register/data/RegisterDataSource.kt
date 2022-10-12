package com.blackholecode.saudedigital.register.data

import com.blackholecode.saudedigital.common.base.RequestCallback

interface RegisterDataSource {

    fun create(email: String, password: String, callback: RequestCallback<Boolean>)

}