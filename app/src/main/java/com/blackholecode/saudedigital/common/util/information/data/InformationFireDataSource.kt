package com.blackholecode.saudedigital.common.util.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback

class InformationFireDataSource : InformationDataSource {

    override fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: Char,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        //TODO: finalizar o registro
    }

    override fun updateProfile(
        uuid: String,
        name: String,
        age: Int,
        mOrF: Char,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        //TODO("Not yet implemented")
    }
}