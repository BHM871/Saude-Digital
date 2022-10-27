package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InformationFireDataSource : InformationDataSource {

    override fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        sex: Int,
        condition: List<Pair<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->

                val uid = res.user?.uid

                if (uid == null) {
                    callback.onFailure("Error in serv")
                } else {
                    FirebaseFirestore.getInstance()
                        .collection("/users")
                        .document(uid)
                        .set(
                            // The variables "sex and "condition" are saved only the
                            // identification values not the actual values, in ProfileFragment
                            // these values are converted to the display values
                            hashMapOf(
                                "uuid" to uid,
                                "email" to email,
                                "name" to name,
                                "age" to age,
                                "sex" to sex,
                                "condition" to condition
                            )
                        )
                        .addOnSuccessListener { me ->
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

    override fun updateProfile(
        uuid: String,
        name: String,
        age: Int,
        sex: Int,
        condition: List<Pair<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {
        val meRef = FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uuid)

        meRef.get()
            .addOnSuccessListener { resMe ->

                val user = resMe.toObject(User::class.java)
                    ?: throw RuntimeException("Error in converting user")
                val newUser = user.copy(
                    name = name,
                    age = age,
                    sex = sex,
                    condition = condition
                )

                meRef.set(newUser)
                    .addOnSuccessListener { resNew ->
                        callback.onSuccess(true)
                    }
                    .addOnFailureListener { exception ->
                        callback.onFailure(exception.message ?: "Error in update")
                    }
                    .addOnCompleteListener {
                        callback.onComplete()
                    }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Error in serv")
                callback.onComplete()
            }
    }

    override fun fetchUser(uuid: String): User? {
        var user: User? = null

        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uuid)
            .get()
            .addOnSuccessListener { res ->
                user = res.toObject(User::class.java)
            }

        return user
    }
}