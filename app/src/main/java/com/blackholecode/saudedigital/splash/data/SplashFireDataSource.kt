package com.blackholecode.saudedigital.splash.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.google.firebase.auth.FirebaseAuth

class SplashFireDataSource : SplashDataSource {

    override fun log(callback: RequestCallback<Boolean>) {
        if (FirebaseAuth.getInstance().uid != null){
            callback.onSuccess(true)
        } else  {
            callback.onFailure("")
        }
    }

}