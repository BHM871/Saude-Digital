package com.blackholecode.saudedigital.login

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView

interface Login {

    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
    }

    interface View : BaseView<Presenter>{
        fun onUserAuthenticate()
        fun onUserUnauthorized(message: String)

        fun displayEmailFailure(resId: Int?)
        fun displayPasswordFailure(resId: Int?)

        fun goToRegisterScreen()
    }

}