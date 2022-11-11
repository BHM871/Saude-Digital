package com.blackholecode.saudedigital.profile.data

import android.app.Activity
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseRemoteDataSource
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFireDataSource(act: Activity) : BaseRemoteDataSource(act), ProfileDataSource {

    override fun fetchProfile(uuid: String, callback: RequestCallback<User>) {

        isComplete = false
        timeOut(callback)

        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uuid)
            .get()
            .addOnSuccessListener { res ->

                val user: User

                try {

                    user = res.toObject(User::class.java)
                        ?: throw RuntimeException(app.getString(R.string.error_user_not_found))

                } catch (e: Exception) {

                    callback.onFailure(app.getString(R.string.error_in_corventing))
                    isComplete = true
                    callback.onComplete()
                    return@addOnSuccessListener

                }

                callback.onSuccess(user)

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