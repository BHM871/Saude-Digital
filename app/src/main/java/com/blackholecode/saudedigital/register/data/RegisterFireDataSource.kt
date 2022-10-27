package com.blackholecode.saudedigital.register.data

import android.net.Uri
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

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
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Error in verification")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

    override fun update(photoUri: Uri, callback: RequestCallback<Boolean>) {
        val uid = FirebaseAuth.getInstance().uid

        if (uid == null || photoUri.lastPathSegment == null) {
            callback.onFailure("User not found")
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
                                val user = document.toObject(User::class.java)
                                val newUser = user?.copy(photoUrl = resDow.toString())

                                if (newUser == null) {
                                    callback.onFailure("User not found")
                                    callback.onComplete()
                                } else {
                                    userRef.set(newUser)
                                        .addOnSuccessListener { resUpd ->
                                            callback.onSuccess(true)
                                        }
                                        .addOnFailureListener { exception ->
                                            callback.onFailure(exception.message ?: "Error in serv")
                                        }
                                        .addOnCompleteListener {
                                            callback.onComplete()
                                        }
                                }

                            }
                            .addOnFailureListener { exception ->
                                callback.onFailure(exception.message ?: "Error in serv")
                                callback.onComplete()
                            }
                    }
                    .addOnFailureListener { exception ->
                        callback.onFailure(exception.message ?: "Error in serv")
                        callback.onComplete()
                    }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Error in serv")
                callback.onComplete()
            }
    }

}