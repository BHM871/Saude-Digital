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
            sex: Int,
            condition: List<Pair<Int?, Int?>?>,
        )

        fun updateProfile(
            name: String,
            age: Int,
            sex: Int,
            condition: List<Pair<Int?, Int?>?>,
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