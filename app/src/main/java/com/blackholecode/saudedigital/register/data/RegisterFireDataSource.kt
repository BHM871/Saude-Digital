package com.blackholecode.saudedigital.register.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFireDataSource : RegisterDataSource {

    override fun create(email: String, password: String, callback: RequestCallback<Boolean>) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    callback.onSuccess(true)
                } else {
                    callback.onFailure("User already exists")
                }
            }
            .addOnFailureListener{ exception ->
                callback.onFailure(exception.message ?: "Error in verification")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

}