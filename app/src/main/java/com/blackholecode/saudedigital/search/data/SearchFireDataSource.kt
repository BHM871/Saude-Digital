package com.blackholecode.saudedigital.search.data

import android.app.Activity
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseRemoteDataSource
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.google.firebase.firestore.FirebaseFirestore

class SearchFireDataSource(act: Activity) : BaseRemoteDataSource(act), SearchDataSource {

    private val docs: Array<String> = arrayOf("obesity", "hypertension", "diabetes", "food")

    private var listSearchAll: MutableList<ModelContent> = mutableListOf()

    override fun search(text: String, callback: RequestCallback<List<ModelContent>>) {

        isComplete = false
        timeOut(callback)

        val listOutput = mutableListOf<ModelContent>()

        for (doc in docs) {
            searchDoc(doc, callback)
        }

        for (model in listSearchAll) {
            if (model.condition!! == text && !listOutput.contains(model)) {
                listOutput.add(model)
            }
        }

        for (model in listSearchAll) {
            if (model.condition!!.lowercase() == text.lowercase() && !listOutput.contains(model)) {
                listOutput.add(model)
            }
        }

        for (model in listSearchAll) {
            if (model.condition!!.startsWith(text) && !listOutput.contains(model)) {
                listOutput.add(model)
            }
        }

        for (model in listSearchAll) {
            if (model.condition!!.lowercase().startsWith(text.lowercase()) && !listOutput.contains(
                    model
                )
            ) {
                listOutput.add(model)
            }
        }

        for (model in listSearchAll) {
            if (model.title!! == text && !listOutput.contains(model)) {
                listOutput.add(model)
            }
        }

        for (model in listSearchAll) {
            if (model.title!!.lowercase() == text.lowercase() && !listOutput.contains(model)) {
                listOutput.add(model)
            }
        }

        for (model in listSearchAll) {
            if (model.title!!.startsWith(text) && !listOutput.contains(model)) {
                listOutput.add(model)
            }
        }

        for (model in listSearchAll) {
            if (model.title!!.lowercase()
                    .startsWith(text.lowercase()) && !listOutput.contains(model)
            ) {
                listOutput.add(model)
            }
        }

        callback.onSuccess(listOutput)
        callback.onComplete()

    }

    private fun searchDoc(doc: String, callback: RequestCallback<List<ModelContent>>) {
        FirebaseFirestore.getInstance()
            .collection("/content")
            .document(doc)
            .collection("content")
            .get()
            .addOnSuccessListener { resDisease ->
                try {

                    for (document in resDisease.documents) {
                        val content =
                            document.toObject(ModelContent::class.java) ?: throw RuntimeException(
                                app.getString(
                                    R.string.error_in_corventing
                                )
                            )
                        listSearchAll.add(content)
                    }

                } catch (e: Exception) {

                    callback.onFailure(e.message ?: app.getString(R.string.error_in_serv))
                    isComplete = true
                    callback.onComplete()
                    return@addOnSuccessListener

                }

            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: app.getString(R.string.error_in_serv))
                callback.onComplete()
            }
    }

}