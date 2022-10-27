package com.blackholecode.saudedigital.common.model

import com.blackholecode.saudedigital.common.util.Condition

data class User(
    val uuid: String? = null,
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,
    var photoUrl: String? = null,
    var age: Int? = null,
    var sex: Int? = null,
    var condition: List<Condition<Int?, Int?>?> = listOf(Condition(null, null)),
)