package com.blackholecode.saudedigital.register.data

import android.net.Uri
import com.blackholecode.saudedigital.common.base.RequestCallback

interface RegisterDataSource {

    fun create(email: String, password: String, callback: RequestCallback<Boolean>)
    fun update(photoUri: Uri, callback: RequestCallback<Boolean>)

}