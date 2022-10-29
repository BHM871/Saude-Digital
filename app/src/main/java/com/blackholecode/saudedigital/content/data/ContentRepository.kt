package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent

class ContentRepository(
    private val localDataSource: ContentLocalDataSource,
    private val remoteDataSource: ContentFireDataSource
) {

    fun fetchContent(typeScreen: String?, callback: RequestCallback<List<ModelContent>>) {
        val uidUser = localDataSource.fetchSession()

        remoteDataSource.clear()
        remoteDataSource.fetchContent(uidUser, typeScreen, object : RequestCallback<List<ModelContent>> {
            override fun onSuccess(data: List<ModelContent>?) {
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