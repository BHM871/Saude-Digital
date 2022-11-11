package com.blackholecode.saudedigital.register.data

import android.app.Activity
import android.net.Uri
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseRemoteDataSource
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

@Suppress("LABEL_NAME_CLASH")
class RegisterFireDataSource(act: Activity) : BaseRemoteDataSource(act), RegisterDataSource {

    override fun create(email: String, password: String, callback: RequestCallback<Boolean>) {

        isComplete = false
        timeOut(callback)

        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    callback.onSuccess(true)
                } else {
                    callback.onFailure(app.getString(R.string.error_user_exists))
                }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(
                    exception.message ?: app.getString(R.string.error_in_verification)
                )
            }
            .addOnCompleteListener {
                isComplete = true
                callback.onComplete()
            }
    }

    override fun update(photoUri: Uri, callback: RequestCallback<Boolean>) {

        isComplete = false
        timeOut(callback)

        val uid = FirebaseAuth.getInstance().uid

        if (uid == null || photoUri.lastPathSegment == null) {
            callback.onFailure(app.getString(R.string.error_user_not_found))
            isComplete = true
            callback.onComplete()
            return
        }

        val imgRef = FirebaseStorage.getInstance().reference
            .child("images/")
            .child(uid)
            .child(photoUri.lastPathSegment!!)

        imgRef.putFile(photoUri)
            .addOnSuccessListener { res ->

                imgRef.downloadUrl
                    .addOnSuccessListener { resDow ->

                        val userRef = FirebaseFirestore.getInstance()
                            .collection("/users")
                            .document(uid)

                        userRef.get()
                            .addOnSuccessListener { document ->
                                val user: User

                                try {

                                    user = document.toObject(User::class.java)
                                        ?: throw RuntimeException(app.getString(R.string.error_in_corventing))

                                } catch (e: Exception) {

                                    callback.onFailure(e.message ?: app.getString(R.string.error_user_not_found))
                                    isComplete = true
                                    callback.onComplete()
                                    return@addOnSuccessListener

                                }

                                val newUser = user.copy(photoUrl = resDow.toString())

                                userRef.set(newUser)
                                    .addOnSuccessListener { resUpd ->
                                        callback.onSuccess(true)
                                    }
                                    .addOnFailureListener { exception ->
                                        callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
                                    }
                                    .addOnCompleteListener {
                                        callback.onComplete()
                                    }

                            }
                            .addOnFailureListener { exception ->
                                callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
                                callback.onComplete()
                            }
                    }
                    .addOnFailureListener { exception ->
                        callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
                        callback.onComplete()
                    }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Error in serv")
                callback.onComplete()
            }
    }

}