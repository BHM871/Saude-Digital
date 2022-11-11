package com.blackholecode.saudedigital.common.util.information.data

import android.app.Activity
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseRemoteDataSource
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.Condition
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InformationFireDataSource(act: Activity) : BaseRemoteDataSource(act), InformationDataSource {

    override fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        sex: Int,
        condition: List<Condition<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {

        timeOut(callback)

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->

                val uid = res.user?.uid

                if (uid == null) {
                    callback.onFailure(app.getString(R.string.error_in_serv))
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
                            callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
                        }
                        .addOnCompleteListener {
                            isComplete = true
                            callback.onComplete()
                        }
                }


            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
                isComplete = true
                callback.onComplete()
            }
    }

    override fun updateProfile(
        uuid: String,
        name: String,
        age: Int,
        sex: Int,
        condition: List<Condition<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {

        timeOut(callback)

        val meRef = FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uuid)

        meRef.get()
            .addOnSuccessListener { resMe ->

                val user: User

                try {

                    user = resMe.toObject(User::class.java)
                        ?: throw RuntimeException(app.getString(R.string.error_in_corventing))

                } catch (e: Exception) {
                    callback.onFailure(e.message ?: app.getString(R.string.error_in_serv))
                    isComplete = true
                    callback.onComplete()
                    return@addOnSuccessListener
                }

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
                        callback.onFailure(exception.message ?: app.getString(R.string.error_in_update))
                    }
                    .addOnCompleteListener {
                        isComplete = true
                        callback.onComplete()
                    }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
                isComplete = true
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

    override fun clear() {
        isComplete = false
    }
}