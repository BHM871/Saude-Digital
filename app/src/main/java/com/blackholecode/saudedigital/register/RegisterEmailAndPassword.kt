package com.blackholecode.saudedigital.register

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView

interface RegisterEmailAndPassword {

    interface Presenter : BasePresenter {
        fun create(email: String, password: String, confirmPassword: String)
    }

    interface View : BaseView<Presenter> {
        fun displayEmailFailure(resId: Int?)
        fun displayPasswordFailure(resId: Int?)

        fun displaySuccessCreate()
        fun displayFailureCreate(message: String)
    }

}