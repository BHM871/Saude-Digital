package com.blackholecode.saudedigital.main.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.google.firebase.auth.FirebaseAuth

class MainLocalDataSource : MainDataSource {

    override fun logout(callback: RequestCallback<Boolean>) {
        FirebaseAuth.getInstance().signOut()
        callback.onSuccess(true)
        callback.onComplete()
    }

    override fun <T> removeCache(cache: Cache<T>) {
        cache.remove()
    }

}