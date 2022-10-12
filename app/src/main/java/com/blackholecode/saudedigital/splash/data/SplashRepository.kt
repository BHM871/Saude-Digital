package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

class SplashRepository(
    private val dataSourceFactory: SplashDataSourceFactory
) {

    fun log(callback: RequestCallback<User>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val remoteDataSource = dataSourceFactory.createRemoteDataSource()

        remoteDataSource.log(object : RequestCallback<User> {
            override fun onSuccess(data: User?) {
                data?.let { localDataSource.putCache(it) }
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