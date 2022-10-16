package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

interface InformationDataSource {

    fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: String,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    fun updateProfile(
        name: String,
        age: Int,
        mOrF: String,
        condition: List<Pair<String, String>>,
        callback: RequestCallback<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    fun fetchSession(): User {
        throw UnsupportedOperationException()
    }

    fun removeCache() {
        throw UnsupportedOperationException()
    }

    fun putCache(data: User) {
        throw UnsupportedOperationException()
    }

}