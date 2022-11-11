package com.blackholecode.saudedigital.content.data

import com.google.firebase.auth.FirebaseAuth

class ContentLocalDataSource : ContentDataSource {

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("User not found")
    }

}