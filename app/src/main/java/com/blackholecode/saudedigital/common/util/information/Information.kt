package com.blackholecode.saudedigital.common.util.information

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView

interface Information {

    interface Presenter : BasePresenter {

        fun create(email: String, password: String, name: String, age: Int, mOrF: Char, condition: List<Pair<String, String>>)

    }
        interface View : BaseView<Presenter> {

        fun displaySuccessCreate()
        fun displayFailureCreate(message: String)

    }
}