package com.blackholecode.saudedigital.common.util

import com.blackholecode.saudedigital.common.base.Cache
import com.blackholecode.saudedigital.common.model.User

object UserSession : Cache<User> {

    override var data: User? = null

    override fun isCached(): Boolean {
        return data != null
    }

    override fun put(data: User) {
        this.data = data
    }

    override fun get() : User? {
        return data
    }

    override fun remove() {
        data = null
    }
}