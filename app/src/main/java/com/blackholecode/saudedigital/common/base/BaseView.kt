package com.blackholecode.saudedigital.common.base

interface BaseView<T> {

    var presenter: T

    fun showProgress(enabled: Boolean)

}