package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.MContent

class ContentRepository(
    private val dataSourceFactory: ContentDataSourceFactory
) {

    fun fetchContent(typeScreen: String, callback: RequestCallback<List<MContent>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val typeUser = localDataSource.fetchSession().condition
        val data = dataSourceFactory.createRemoteDataSource()

        if (typeUser != null) {
            data.fetchContent(typeUser, typeScreen, callback)
        }
    }

}