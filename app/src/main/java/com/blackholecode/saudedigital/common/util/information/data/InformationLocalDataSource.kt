package com.blackholecode.saudedigital.common.util.information.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.UserSession
import java.util.*

class InformationLocalDataSource(
    private val userCache: Cache<User>
) : InformationDataSource {

    override fun fetchSession() : User {
//        FirebaseFirestore.getInstance()
//            .collection("/users")
//            .document("uuid", FirebaseAuth.getInstance().uid)
//            .get()
        return UserSession.get() ?: User("jbckjabc", password = "jbcasbjchas")
    }

    override fun removeCache() {
        userCache.remove()
    }

}