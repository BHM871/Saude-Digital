package com.blackholecode.saudedigital.register.data

import android.os.Handler
import android.os.Looper
import com.blackholecode.saudedigital.common.base.RequestCallback

class RegisterFireDataSource : RegisterDataSource {

    override fun create(email: String, password: String, callback: RequestCallback<Boolean>) {
        //TODO: verificar se email e senha ja existem
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onSuccess(null)
            callback.onComplete()
        }, 1000)
    }

}