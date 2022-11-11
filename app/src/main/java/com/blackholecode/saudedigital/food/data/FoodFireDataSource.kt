package com.blackholecode.saudedigital.food.data

import android.app.Activity
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseRemoteDataSource
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.google.firebase.firestore.FirebaseFirestore

class FoodFireDataSource(act: Activity) : BaseRemoteDataSource(act), FoodDataSource {

    override fun searchSimilar(type: String, callback: RequestCallback<List<ModelContent>>) {

        isComplete = false
        timeOut(callback)

        FirebaseFirestore.getInstance()
            .collection("/content")
            .document(type)
            .collection("content")
            .get()
            .addOnSuccessListener { resSimilar ->

                val list = mutableListOf<ModelContent>()

                try {
                    for (document in resSimilar.documents) {
                        val similar = document.toObject(ModelContent::class.java) ?: throw RuntimeException(app.getString(R.string.error_in_corventing))
                        list.add(similar)
                    }
                } catch (e: Exception) {
                    callback.onFailure(e.message ?: app.getString(R.string.error_in_serv))
                    isComplete = true
                    callback.onComplete()
                    return@addOnSuccessListener
                }

                callback.onSuccess(list)

            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: app.getString(R.string.error_in_search, "Food"))
            }
            .addOnCompleteListener {
                isComplete = true
                callback.onComplete()
            }

    }
}