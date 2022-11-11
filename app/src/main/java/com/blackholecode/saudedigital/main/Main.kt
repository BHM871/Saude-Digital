package com.blackholecode.saudedigital.main

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView

interface Main {

    interface Presenter : BasePresenter {
        fun logout()
    }

    interface View : BaseView<Presenter> {
        fun displayLogoutSuccess()
    }

}