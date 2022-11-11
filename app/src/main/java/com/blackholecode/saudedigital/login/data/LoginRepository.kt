package com.blackholecode.saudedigital.login.data

import com.blackholecode.saudedigital.common.base.RequestCallback

class LoginRepository(
    private val remoteDataSource: LoginFireDataSource
) {

    fun login(email: String, password: String, callback: RequestCallback<Boolean>) {
        remoteDataSource.login(email, password, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }

}