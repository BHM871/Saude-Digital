package com.blackholecode.saudedigital.common.base

interface Cache<T> {
    var data: T?

    fun isCached() : Boolean
    
    fun put(data: T)
    
    fun get(): T?

    fun remove()
}