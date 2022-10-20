package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.UserSession

class ContentLocalDataSource(
    private val userSession: Cache<User>
) : ContentDataSource {

    override fun fetchSession() : User {
//        FirebaseFirestore.getInstance()
//            .collection("/users")
//            .document("uuid", FirebaseAuth.getInstance().uid)
//            .get()
        return UserSession.get() ?: User("jbckjabc", password = "jbcasbjchas", condition = listOf(
            Pair("Obesidade", "Tipo 1")
        ))
    }

    override fun clear(){
        userSession.remove()
    }

}