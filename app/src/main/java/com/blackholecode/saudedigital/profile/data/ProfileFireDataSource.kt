package com.blackholecode.saudedigital.profile.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

class ProfileFireDataSource : ProfileDataSource {

    override fun fetchProfile(uuid: String, callback: RequestCallback<User>) {
        //TODO: logica para buscar o usuario
        callback.onComplete()
    }

}