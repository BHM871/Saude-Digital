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

    fun updateProfile(name: String, age: Int, mOrF: String, condition: List<Pair<String, String>>, callback: RequestCallback<Boolean>) {
        remoteDataSource.updateProfile(
            name,
            age,
            mOrF,
            condition,
            object : RequestCallback<Boolean> {
                override fun onSuccess(data: Boolean?) {
                    //TODO: tirar esse comentario localDataSource.removeCache()
                    val user = User("a", "a@a.com", "123123", name, age, mOrF, condition)
                    localDataSource.putCache(user)
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