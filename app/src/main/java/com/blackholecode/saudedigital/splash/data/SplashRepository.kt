package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

class SplashRepository(
    private val remoteDataSource: SplashFireDataSource,
) {

    fun log(callback: RequestCallback<User>) {
        remoteDataSource.log(object : RequestCallback<User> {
            override fun onSuccess(data: User?) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String?) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }

}