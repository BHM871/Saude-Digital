package com.blackholecode.saudedigital.register.data

import android.net.Uri
import com.blackholecode.saudedigital.common.base.RequestCallback

class RegisterRepository(
    private val data: RegisterDataSource
) {
    fun create(email: String, password: String, callback: RequestCallback<Boolean>) {
        data.create(email, password, callback)
    }

    fun updateUser(photoUri: Uri, callback: RequestCallback<Boolean>) {
        data.update(photoUri, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }
}