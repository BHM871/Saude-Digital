package com.blackholecode.saudedigital.content

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView
import com.blackholecode.saudedigital.common.model.Food
import com.blackholecode.saudedigital.common.model.MContent
import com.blackholecode.saudedigital.common.model.Video

interface Content {

    interface Presenter : BasePresenter {
        fun fetchContent(type: String)
    }

    interface View : BaseView<Presenter> {
        fun displayRequestSuccessful(data: List<MContent>)
        fun displayRequestEmptyList()
        fun displayRequestFailure(message: String)
    }

}