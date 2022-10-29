package com.blackholecode.saudedigital.content.data

import android.util.Log
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.Condition
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ContentFireDataSource : ContentDataSource {

    private val diseases: HashMap<Int, String> =
        hashMapOf(1 to "obesity", 2 to "hypertension", 3 to "diabetes")
    private val typeDisease: HashMap<Int, String> = hashMapOf(1 to "1", 2 to "2")
    private var listContent: MutableList<ModelContent> = mutableListOf()

    override fun fetchContent(
        uidUser: String,
        typeScreen: String?,
        callback: RequestCallback<List<ModelContent>>
    ) {
        if (typeScreen == null) {
            fetchHome(uidUser, callback)
        } else {
            fetchNotHome(uidUser, typeScreen, callback)
        }
    }

    override fun clear() {
        listContent.clear()
    }

    private fun fetchHome(uidUser: String, callback: RequestCallback<List<ModelContent>>) {
        var conditionUser: List<Condition<Int?, Int?>?>

        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uidUser)
            .get()
            .addOnSuccessListener { resUser ->

                conditionUser = resUser.toObject(User::class.java)?.condition!!

                val contentRef =
                    FirebaseFirestore.getInstance().collection("/content")

                val listDocs: List<DocumentReference> = listOf(
                    contentRef.document("obesity"),
                    contentRef.document("hypertension"),
                    contentRef.document("diabetes"),
                    contentRef.document("food")
                )

                for (docRef in listDocs) {

                    searchWithoutIndication(true, docRef, callback)

                }

            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", exception.message ?: "Error home")
                callback.onFailure(exception.message ?: "Error in fetch user")
                callback.onComplete()
            }
    }

    private fun fetchNotHome(
        uidUser: String,
        typeScreen: String,
        callback: RequestCallback<List<ModelContent>>
    ) {
        var conditionUser: List<Condition<Int?, Int?>?>

        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uidUser)
            .get()
            .addOnSuccessListener { resUser ->

                conditionUser = resUser.toObject(User::class.java)?.condition!!

                val docRef =
                    FirebaseFirestore.getInstance().collection("/content").document(typeScreen)

                for (condition in conditionUser) {

                    if (typeScreen == diseases[condition?.disease_id]) {
                        searchWithIndication(
                            false,
                            typeDisease[condition?.type]!!,
                            docRef,
                            callback
                        )
                    } else {
                        searchWithoutIndication(false, docRef, callback)
                    }

                }

            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", exception.message ?: "Error $typeScreen")
                callback.onFailure(exception.message ?: "Error in fetch user")
                callback.onComplete()
            }
    }

    private fun searchWithoutIndication(
        isHome: Boolean,
        docRef: DocumentReference,
        callback: RequestCallback<List<ModelContent>>
    ) {
        docRef.collection("content")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { resContent ->

                if (!isHome) listContent.clear()

                listContent.addAll(resContent.toObjects(ModelContent::class.java))

                if (!isHome) {
                    output(callback)
                } else if (docRef.id == "food") {
                    output(callback)
                }

            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", exception.message ?: "Error ${docRef.id}")
                callback.onFailure(exception.message ?: "Error in search ${docRef.id}")
                callback.onComplete()
            }
    }

    private fun searchWithIndication(
        isHome: Boolean,
        type: String,
        docRef: DocumentReference,
        callback: RequestCallback<List<ModelContent>>
    ) {
        docRef.collection("content")
            .whereEqualTo("type", type)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { resFilterContent ->

                if (!isHome) listContent.clear()

                listContent = resFilterContent.toObjects(ModelContent::class.java)

                docRef.collection("content")
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener { resContent ->

                        val allContent = resContent.toObjects(ModelContent::class.java)

                        for (content in allContent) {
                            var exist = false

                            for (itemList in listContent) {

                                if (itemList.id == content.id) {
                                    exist = true
                                }

                            }

                            if (!exist) {
                                listContent.add((listContent.lastIndex.plus(1)), content)
                            }

                            callback.onSuccess(listContent)
                        }

                    }
                    .addOnFailureListener { exception ->
                        Log.e("Firebase", exception.message ?: "Error ${docRef.id}")
                        callback.onFailure(exception.message ?: "Error in search ${docRef.id}")
                    }
                    .addOnCompleteListener {
                        callback.onComplete()
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", exception.message ?: "Error ${docRef.id}")
                callback.onFailure(exception.message ?: "Error in search ${docRef.id}")
                callback.onComplete()
            }
    }

    private fun output(callback: RequestCallback<List<ModelContent>>) {
        listContent.sortBy { it.timestamp?.seconds }
        callback.onSuccess(listContent)
        callback.onComplete()
    }

}