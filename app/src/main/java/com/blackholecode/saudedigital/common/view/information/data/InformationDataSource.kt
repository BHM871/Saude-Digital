package com.blackholecode.saudedigital.common.view.information.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

interface InformationDataSource {

    fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        sex: Int,
        condition: List<Pair<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    fun updateProfile(
        uuid: String,
        name: String,
        age: Int,
        sex: Int,
        condition: List<Pair<Int?, Int?>?>,
        callback: RequestCallback<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    fun fetchSession(): String {
        throw UnsupportedOperationException()
    }

    fun fetchUser(uuid: String) : User?

    fun removeCache() {
        throw UnsupportedOperationException()
    }

    fun putCache(data: User) {
        throw UnsupportedOperationException()
    }

}