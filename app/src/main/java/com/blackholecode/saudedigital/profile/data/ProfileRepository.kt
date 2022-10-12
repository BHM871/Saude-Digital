package com.blackholecode.saudedigital.profile.data

import android.service.notification.Condition
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

    fun updateProfile(name: String, age: Int, mOrF: Char, condition: List<Pair<String, String>>, callback: RequestCallback<User>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val uuid = localDataSource.fetchSession().uuid
        val data = dataSourceFactory.createRemoteDataSource()

        data.updateProfile(
            uuid ?: throw RuntimeException(),
            name,
            age,
            mOrF,
            condition,
            object : RequestCallback<User> {
            override fun onSuccess(data: User?) {
                localDataSource.removeCache()
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