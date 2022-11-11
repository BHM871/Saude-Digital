package com.blackholecode.saudedigital.common.util.information.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User
import com.google.firebase.auth.FirebaseAuth

class InformationLocalDataSource(
    private val userSession: Cache<User>
) : InformationDataSource {

    override fun fetchSession() : String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("User not found")
    }

    override fun fetchUser(uuid: String) : User? {
        return userSession.get()
    }

    override fun putCache(data: User) {
        userSession.put(data)
    }

    override fun removeCache() {
        userSession.remove()
    }

    override fun clear() {
        userSession.remove()
    }

}