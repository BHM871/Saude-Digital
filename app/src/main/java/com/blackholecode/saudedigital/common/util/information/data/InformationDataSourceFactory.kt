package com.blackholecode.saudedigital.common.util.information.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

class InformationDataSourceFactory(private val userCache: Cache<User>) {

    fun createLocalDataSource() : InformationDataSource {
        return InformationLocalDataSource(userCache)
    }

    fun createRemoteDataSource() : InformationDataSource {
        return InformationFireDataSource()
    }

}