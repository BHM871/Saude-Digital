package com.blackholecode.saudedigital.search.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.google.firebase.firestore.FirebaseFirestore

class SearchFireDataSource : SearchDataSource {

    private val docs: Array<String> = arrayOf("obesity", "hypertension", "diabetes", "food")
    
    private var listSearchAll: MutableList<ModelContent> = mutableListOf()

    override fun search(text: String, callback: RequestCallback<List<ModelContent>>) {

        val litOutput = mutableListOf<ModelContent>()

        for (doc in docs){
            searchAll(doc, callback)
        }
        
        for (model in listSearchAll){
            if (model.condition?.startsWith(text)!!) {
                litOutput.add(model)
            }
        }

        for (model in listSearchAll){
            if (model.condition!! == text) {
                litOutput.add(model)
            }
        }

        for (model in listSearchAll){
            if (model.title?.startsWith(text)!!) {
                litOutput.add(model)
            }
        }

        for (model in listSearchAll){
            if (model.title!! == text) {
                litOutput.add(model)
            }
        }

        callback.onSuccess(litOutput)
        callback.onComplete()

    }
    
    private fun searchAll(doc: String, callback: RequestCallback<List<ModelContent>>) {
        FirebaseFirestore.getInstance()
            .collection("/content")
            .document(doc)
            .collection("content")
            .get()
            .addOnSuccessListener { resDisease ->
                try {
                    for (document in resDisease.documents) {
                        val content = document.toObject(ModelContent::class.java) ?: throw RuntimeException("Error in transform")
                        listSearchAll.add(content)
                    }
                } catch (e: Exception) {
                    callback.onFailure(e.message ?: "Error in serv")
                    callback.onComplete()
                }

            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Error ins search")
                callback.onComplete()
            }
    }

}