package com.blackholecode.saudedigital.login.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginFireDataSource : LoginDataSource {

    override fun login(email: String, password: String, callback: RequestCallback<Boolean>) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->

                if (res != null) {
                    callback.onSuccess(true)
                } else {
                    callback.onFailure("Sing in error")
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