package com.blackholecode.saudedigital.common.model

data class User(
    val uuid: String? = null,
    var email: String? = null,
    var password: String,
    var name: String? = null,
    var age: Int? = null,
    var sex: String? = null,
    var condition: List<Pair<String, String>>? = null
)