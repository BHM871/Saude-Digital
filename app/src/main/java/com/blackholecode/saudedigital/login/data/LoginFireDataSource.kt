package com.blackholecode.saudedigital.login.data

import android.os.Handler
import android.os.Looper
import com.blackholecode.saudedigital.common.base.RequestCallback

class LoginFireDataSource : LoginDataSource {

    override fun login(email: String, password: String, callback: RequestCallback<Boolean>) {
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onSuccess(null)
            callback.onComplete()
        }, 1000)
    }

}