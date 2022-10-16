package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.MContent

class ContentRepository(
    private val localDataSource: ContentLocalDataSource,
    private val remoteDataSource: ContentFireDataSource
) {

    fun fetchContent(typeScreen: String, callback: RequestCallback<List<MContent>>) {
        val typeUser = localDataSource.fetchSession().condition

        if (typeUser != null) {
            remoteDataSource.fetchContent(typeUser, typeScreen, callback)
        }
    }

}