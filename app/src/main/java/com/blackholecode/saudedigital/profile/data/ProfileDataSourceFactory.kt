package com.blackholecode.saudedigital.profile.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

class ProfileDataSourceFactory(private val userCache: Cache<User>) {

    fun createLocalDataSource() : ProfileDataSource {
        return ProfileLocalDataSource(userCache)
    }

    fun createRemoteDataSource() : ProfileDataSource {
        return ProfileFireDataSource()
    }

    fun createFromUser() : ProfileDataSource {
        return if (userCache.isCached()) {
            createLocalDataSource()
        } else {
            createRemoteDataSource()
        }

    }

}