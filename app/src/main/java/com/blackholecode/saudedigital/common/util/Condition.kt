package com.blackholecode.saudedigital.common.util

data class Condition<F, S>(
    var disease_id: F? = null,
    var type: S? = null
)