package com.blackholecode.saudedigital.profile.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.UserSession

class ProfileLocalDataSource(
    private val userSession: Cache<User>
) : ProfileDataSource {

    override fun fetchProfile(uuid: String, callback: RequestCallback<User>) {
        val user = userSession.get()

        if (user != null){
            callback.onSuccess(user)
        } else {
            callback.onFailure("User not found")
        }

        callback.onComplete()
    }

    override fun fetchSession() : User {
//        FirebaseFirestore.getInstance()
//            .collection("/users")
//            .document("uuid", FirebaseAuth.getInstance().uid)
//            .get()
        return UserSession.get() ?: User("jbckjabc", password = "jbcasbjchas")
    }

    override fun putCache(data: User) {
        userSession.put(data)
    }

}