package com.blackholecode.saudedigital.profile.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFireDataSource : ProfileDataSource {

    override fun fetchProfile(uuid: String, callback: RequestCallback<User>) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uuid)
            .get()
            .addOnSuccessListener { res ->

                val user = res.toObject(User::class.java)

                if (user != null){
                    callback.onSuccess(user)
                } else {
                    callback.onFailure("Error in serv")
                }

            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Error in serv")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

}