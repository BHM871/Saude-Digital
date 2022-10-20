package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

class InformationRepository(
    private val localDataSource: InformationLocalDataSource,
    private val remoteDataSource: InformationFireDataSource
) {

    fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: String,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        remoteDataSource.create(email, password, name, age, mOrF, condition, callback)
    }

    fun updateProfile(name: String, age: Int, sex: String, condition: List<Pair<String, String>>, callback: RequestCallback<Boolean>) {
        val uuid = localDataSource.fetchSession()?.uuid ?: throw RuntimeException("User not found")
        remoteDataSource.updateProfile(
            uuid,
            name,
            age,
            sex,
            condition,
            object : RequestCallback<Boolean> {
                override fun onSuccess(data: Boolean?) {
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

    fun fetchUser(callback: RequestCallback<User>) {
        val user = localDataSource.fetchSession()

        if (user != null) {
            callback.onSuccess(user)
        } else {
            callback.onFailure("User not found")
        }
        callback.onComplete()

    }

}