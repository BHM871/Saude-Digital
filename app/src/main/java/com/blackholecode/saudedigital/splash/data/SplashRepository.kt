package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

class SplashRepository(
    private val remoteDataSource: SplashFireDataSource,
) {

    fun log(callback: RequestCallback<Boolean>) {
        remoteDataSource.log(object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
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