package com.blackholecode.saudedigital.food

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView
import com.blackholecode.saudedigital.common.model.ModelContent

interface Food {

    interface Presenter : BasePresenter {

        fun searchSimilar(type: String)

    }

    interface View : BaseView<Presenter> {

        fun displayRequestFullList(data: List<ModelContent>)
        fun displayRequestEmptyList()
        fun displayRequestFailure(message: String)

    }

}