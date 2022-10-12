package com.blackholecode.saudedigital.common.util.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback

interface InformationDataSource {

    fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: Char,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    )

}