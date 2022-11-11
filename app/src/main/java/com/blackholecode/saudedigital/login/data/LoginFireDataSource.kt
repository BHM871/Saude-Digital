package com.blackholecode.saudedigital.login.data

import android.app.Activity
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseRemoteDataSource
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.google.firebase.auth.FirebaseAuth

class LoginFireDataSource(act: Activity) : BaseRemoteDataSource(act), LoginDataSource {

    override fun login(email: String, password: String, callback: RequestCallback<Boolean>) {

        isComplete = false
        timeOut(callback)

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->

                if (res != null) {
                    callback.onSuccess(true)
                } else {
                    callback.onFailure(app.getString(R.string.error_in_login))
                }

            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
            }
            .addOnCompleteListener {
                isComplete = true
                callback.onComplete()
            }
    }

}