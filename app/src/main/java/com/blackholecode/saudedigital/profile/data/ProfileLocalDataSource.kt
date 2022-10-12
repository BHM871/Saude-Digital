package com.blackholecode.saudedigital.profile.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import java.util.*

class ProfileLocalDataSource(
    private val userCache: Cache<User>
) : ProfileDataSource {

    override fun fetchProfile(uuid: String, callback: RequestCallback<User>) {
        val user = userCache.get()

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
        return User(uuid = UUID.randomUUID().toString())
    }

    override fun putCache(data: User) {
        userCache.put(data)
    }

    override fun removeCache() {
        userCache.remove()
    }

}