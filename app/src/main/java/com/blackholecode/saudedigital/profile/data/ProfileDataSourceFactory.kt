package com.blackholecode.saudedigital.profile.data

import android.app.Activity
import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

class ProfileDataSourceFactory(
    private val activity: Activity,
    private val userCache: Cache<User>
) {

    fun createLocalDataSource() : ProfileDataSource {
        return ProfileLocalDataSource(userCache)
    }

    private fun createRemoteDataSource() : ProfileDataSource {
        return ProfileFireDataSource(activity)
    }

    fun createFromUser() : ProfileDataSource {
        return if (userCache.isCached()) {
            createLocalDataSource()
        } else {
            createRemoteDataSource()
        }

    }

}