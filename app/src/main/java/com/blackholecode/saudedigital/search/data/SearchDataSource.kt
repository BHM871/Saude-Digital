package com.blackholecode.saudedigital.search.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent

interface SearchDataSource {

    fun search(text: String, callback: RequestCallback<List<ModelContent>>) { throw UnsupportedOperationException() }

    fun putCache(data: List<ModelContent>) { throw UnsupportedOperationException() }
    fun getCache() : List<ModelContent>? { throw UnsupportedOperationException() }

}