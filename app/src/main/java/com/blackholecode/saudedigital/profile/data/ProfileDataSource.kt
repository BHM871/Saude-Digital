package com.blackholecode.saudedigital.profile.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

interface ProfileDataSource {

    fun fetchSession() : User {throw UnsupportedOperationException("Not yet implementation")}
    fun fetchProfile(uuid: String, callback: RequestCallback<User>) {throw UnsupportedOperationException("Not yet implementation")}

    fun putCache(data: User) {throw UnsupportedOperationException("Not yet implementation")}
    fun removeCache() {throw UnsupportedOperationException("Not yet implementation")}

}