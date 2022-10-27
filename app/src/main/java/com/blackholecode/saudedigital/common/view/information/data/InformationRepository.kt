package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.Condition

class InformationRepository(
    private val dataSourceFactory: InformationDataSourceFactory
) {

    fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        sex: Int,
        condition: List<Condition<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {
        dataSourceFactory.createRemote()
            .create(email, password, name, age, sex, condition, callback)
    }

    fun updateProfile(
        name: String,
        age: Int,
        sex: Int,
        condition: List<Condition<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {
        val localDataSource = dataSourceFactory.createLocal()
        val uuid = localDataSource.fetchSession()
        dataSourceFactory.createRemote().updateProfile(
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
        val localDataSource = dataSourceFactory.createLocal()
        val uidUser = localDataSource.fetchSession()
        val data = dataSourceFactory.createFromUser()

        val user = data.fetchUser(uidUser)

        if (user != null) {
            callback.onSuccess(user)
        } else {
            callback.onFailure("User not found")
        }

        callback.onComplete()

    }

}