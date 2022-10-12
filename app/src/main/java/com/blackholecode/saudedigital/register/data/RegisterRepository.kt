package com.blackholecode.saudedigital.register.data

import com.blackholecode.saudedigital.common.base.RequestCallback

class RegisterRepository(
    private val data: RegisterDataSource
) {
    fun create(email: String, password: String, callback: RequestCallback<Boolean>) {
        data.create(email, password, callback)
    }
}