package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.UserSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class InformationLocalDataSource(
    private val userSession: Cache<User>
) : InformationDataSource {

    override fun fetchSession() : User? {
        return userSession.get()
    }

    override fun putCache(data: User) {
        userSession.put(data)
    }

    override fun removeCache() {
        userSession.remove()
    }

}