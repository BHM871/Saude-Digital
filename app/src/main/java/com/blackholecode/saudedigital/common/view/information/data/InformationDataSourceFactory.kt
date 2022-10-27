package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

class InformationDataSourceFactory(
    private val userSession: Cache<User>
) {

    fun createLocal() : InformationDataSource {
        return InformationLocalDataSource(userSession)
    }

    fun createRemote() : InformationFireDataSource {
        return InformationFireDataSource()
    }

    fun createFromUser() : InformationDataSource {
        return if (userSession.isCached()){
            createLocal()
        } else {
            createRemote()
        }
    }

}