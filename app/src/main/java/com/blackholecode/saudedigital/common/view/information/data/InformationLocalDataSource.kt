package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.UserSession

class InformationLocalDataSource(
    private val userSession: Cache<User>
) : InformationDataSource {

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

    override fun removeCache() {
        userSession.remove()
    }

}