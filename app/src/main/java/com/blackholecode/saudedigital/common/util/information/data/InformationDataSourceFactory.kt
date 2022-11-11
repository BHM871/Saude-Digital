package com.blackholecode.saudedigital.common.util.information.data

import android.app.Activity
import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

class InformationDataSourceFactory(
    private val activity: Activity,
    private val userSession: Cache<User>
) {

    fun createLocal() : InformationDataSource {
        return InformationLocalDataSource(userSession)
    }

    fun createRemote() : InformationFireDataSource {
        return InformationFireDataSource(activity)
    }

    fun createFromUser() : InformationDataSource {
        return if (userSession.isCached()){
            createLocal()
        } else {
            createRemote()
        }
    }

}