package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.MContent

class ContentFireDataSource : ContentDataSource {

    override fun fetchContent(
        typeUser: List<Pair<String, String>>,
        typeScreen: String,
        callback: RequestCallback<List<MContent>>
    ) {
        //TODO("Not yet implemented")
        callback.onSuccess(listOf())
    }
}