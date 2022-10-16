package com.blackholecode.saudedigital.common.view.information

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView
import com.blackholecode.saudedigital.common.model.User

interface Information {

    interface Presenter : BasePresenter {

        fun create(
            email: String,
            password: String,
            name: String,
            age: Int,
            mOrF: String,
            condition: List<Pair<String, String>>
        )

        fun updateProfile(
            name: String,
            age: Int,
            mOrF: String,
            condition: List<Pair<String, String>>
        )

        fun fetchUser()

    }

    interface View : BaseView<Presenter> {
        fun displaySuccessFetch(data: User)
        fun displayFailureFetch(message: String)

        fun displaySuccessCreate()
        fun displayFailureCreate(message: String)

        fun displaySuccessUpdate()
        fun displayFailureUpdate(message: String)
    }
}