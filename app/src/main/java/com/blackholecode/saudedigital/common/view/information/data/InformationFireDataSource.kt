package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback

class InformationFireDataSource : InformationDataSource {

    override fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: String,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        //TODO: finalizar o registro
        callback.onSuccess(data = true)
        callback.onComplete()
    }

    override fun updateProfile(
        name: String,
        age: Int,
        mOrF: String,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        //TODO("Not yet implemented")
        callback.onSuccess(data = true)
        callback.onComplete()    }
}