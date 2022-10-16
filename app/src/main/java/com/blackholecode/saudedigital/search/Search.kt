package com.blackholecode.saudedigital.search

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView

interface Search {

    interface Presenter : BasePresenter {

        fun searchVideos(title: String?)

    }

    interface View : BaseView<Presenter> {

        fun displayRequestEmptyList()

    }

}