package com.blackholecode.saudedigital.profile.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

class ProfileRepository(
    private val dataSourceFactory: ProfileDataSourceFactory
) {

    fun fetchProfile(callback: RequestCallback<User>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val uuid = localDataSource.fetchSession().uuid
        val data = dataSourceFactory.createFromUser()

        data.fetchProfile(uuid ?: throw RuntimeException(), object : RequestCallback<User> {
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