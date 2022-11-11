package com.blackholecode.saudedigital.register

import android.net.Uri
import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter {
        fun update(photoUri: Uri)
    }

    interface View : BaseView<Presenter> {
        fun displayUpdateSuccess()
        fun displayUpdateFailure(message: String)
    }

}