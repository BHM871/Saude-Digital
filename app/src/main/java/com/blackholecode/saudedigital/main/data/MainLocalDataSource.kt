package com.blackholecode.saudedigital.main.data

import com.blackholecode.saudedigital.common.base.Cache

class MainLocalDataSource : MainDataSource {

    override fun <T> removeCache(cache: Cache<T>) {
        cache.remove()
    }

}