package com.blackholecode.saudedigital.main.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.util.UserSession
import com.blackholecode.saudedigital.search.util.ListSearchCache

class MainRepository(
    private val localDataSource : MainDataSource,
) {

    fun logout(callback: RequestCallback<Boolean>) {
        localDataSource.logout(object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                localDataSource.removeCache(UserSession)
                localDataSource.removeCache(ListSearchCache)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })

    }

}