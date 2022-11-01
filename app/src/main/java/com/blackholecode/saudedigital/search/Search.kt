package com.blackholecode.saudedigital.search

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView
import com.blackholecode.saudedigital.common.model.ModelContent

interface Search {

    interface Presenter : BasePresenter {

        fun searchVideos(text: String?)

    }

    interface View : BaseView<Presenter> {

        fun displayRequestFullList(data: List<ModelContent>)
        fun displayRequestEmptyList()
        fun displayRequestFailure(message: String)

    }

}