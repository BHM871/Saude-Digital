package com.blackholecode.saudedigital.common.model

data class User(
    val uuid: String? = null,
    var name: String? = null,
    var sex: Char? = null,
    var age: Int? = null,
    var condition: List<Pair<String, String>>? = null
)