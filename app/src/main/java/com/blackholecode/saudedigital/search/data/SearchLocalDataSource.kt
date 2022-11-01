package com.blackholecode.saudedigital.search.data

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.ModelContent

class SearchLocalDataSource(
    private val listCache: Cache<List<ModelContent>>
) : SearchDataSource {

    override fun putCache(data: List<ModelContent>) {
        listCache.put(data)
    }

    override fun getCache(): List<ModelContent>? {
        return listCache.get()
    }

}