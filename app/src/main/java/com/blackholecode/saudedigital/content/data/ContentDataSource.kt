package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent

interface ContentDataSource {
    fun fetchContent(uidUser: String, typeScreen: String?, callback: RequestCallback<List<ModelContent>>) { throw UnsupportedOperationException() }
    fun fetchSession() : String { throw UnsupportedOperationException() }

    fun clear(callback: RequestCallback<List<ModelContent>>) { throw UnsupportedOperationException() }
}