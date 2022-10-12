package com.blackholecode.saudedigital.common.util.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback

class InformationRepository(
    private val dataSourceFactory: InformationDataSourceFactory
) {

    fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: Char,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        dataSourceFactory.createRemoteDataSource().create(email, password, name, age, mOrF, condition, callback)
    }

    fun updateProfile(name: String, age: Int, mOrF: Char, condition: List<Pair<String, String>>, callback: RequestCallback<Boolean>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val uuid = localDataSource.fetchSession().uuid
        val data = dataSourceFactory.createRemoteDataSource()

        data.updateProfile(
            uuid ?: throw RuntimeException(),
            name,
            age,
            mOrF,
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

}