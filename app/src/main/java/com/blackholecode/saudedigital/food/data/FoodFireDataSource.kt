package com.blackholecode.saudedigital.food.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.google.firebase.firestore.FirebaseFirestore

class FoodFireDataSource : FoodDataSource {

    override fun searchSimilar(type: String, callback: RequestCallback<List<ModelContent>>) {
        FirebaseFirestore.getInstance()
            .collection("/content")
            .document(type)
            .collection("content")
            .get()
            .addOnSuccessListener { resSimilar ->

                val list = mutableListOf<ModelContent>()

                try {
                    for (document in resSimilar.documents) {
                        val similar = document.toObject(ModelContent::class.java) ?: throw RuntimeException("Error in converting")
                        list.add(similar)
                    }
                } catch (e: Exception) {
                    callback.onFailure(e.message ?: "Error in serv")
                    callback.onComplete()
                }

                callback.onSuccess(list)

            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Error in search similar")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }

    }
}