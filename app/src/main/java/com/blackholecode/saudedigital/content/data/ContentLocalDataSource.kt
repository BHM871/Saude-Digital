package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.UserSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ContentLocalDataSource(
    private val userSession: Cache<User>
) : ContentDataSource {

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("User not found")
    }

    override fun clear() {
        userSession.remove()
    }

}