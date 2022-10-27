package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.MContent

class ContentRepository(
    private val localDataSource: ContentLocalDataSource,
    private val remoteDataSource: ContentFireDataSource
) {

    fun fetchContent(typeScreen: String, callback: RequestCallback<List<MContent>>) {
        val uidUser = localDataSource.fetchSession()

        remoteDataSource.fetchContent(uidUser, typeScreen, callback)
    }

}