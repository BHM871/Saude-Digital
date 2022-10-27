package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.MContent
import com.blackholecode.saudedigital.common.model.User

interface ContentDataSource {

    fun fetchContent(uidUser: String, typeScreen: String, callback: RequestCallback<List<MContent>>) { throw UnsupportedOperationException() }

    fun fetchSession() : String { throw UnsupportedOperationException() }

    fun clear() { throw UnsupportedOperationException() }

}