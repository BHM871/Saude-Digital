package com.blackholecode.saudedigital.profile.data

import android.util.Log
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

                var user: User? = null

                try {
                    user = res.toObject(User::class.java)
                } catch (e: Exception) {
                    e.message?.let { Log.e("Converting user error:", it) }
                }

                if (user != null){
                    callback.onSuccess(user)
                } else {
                    callback.onFailure("User not found")
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