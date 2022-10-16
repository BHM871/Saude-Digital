package com.blackholecode.saudedigital.main.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.util.UserSession

class MainRepository(
    private val localDataSource : MainDataSource,
    private val remoteDataSource : MainDataSource
) {

    fun logout(callback: RequestCallback<Boolean>) {
        remoteDataSource.logout(object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                localDataSource.removeCache(UserSession)
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