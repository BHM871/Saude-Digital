package com.blackholecode.saudedigital.main.data

import com.blackholecode.saudedigital.common.base.RequestCallback

class MainFireDataSource : MainDataSource {

    override fun logout(callback: RequestCallback<Boolean>) {
        //TODO: logout
        callback.onSuccess(true)
        callback.onComplete()
    }

}