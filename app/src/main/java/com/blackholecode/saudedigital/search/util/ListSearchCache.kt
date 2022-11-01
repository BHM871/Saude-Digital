package com.blackholecode.saudedigital.search.util

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.ModelContent

object ListSearchCache : Cache<List<ModelContent>> {

    override var data: List<ModelContent>? = null

    override fun isCached(): Boolean {
        return data != null
    }

    override fun put(data: List<ModelContent>) {
        this.data = data
    }

    override fun get(): List<ModelContent>? {
        return data
    }

    override fun remove() {
        data = null
    }
}