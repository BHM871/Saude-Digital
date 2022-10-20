package com.blackholecode.saudedigital.splash

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView
import com.blackholecode.saudedigital.common.model.User

interface Splash {

    interface Presenter : BasePresenter {
        fun log()
    }

    interface View : BaseView<Presenter> {
        fun goToMainScreen()
        fun goToLoginScreen()
    }

}