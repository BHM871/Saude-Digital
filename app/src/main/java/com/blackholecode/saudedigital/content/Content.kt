package com.blackholecode.saudedigital.content

import android.app.Activity
import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView
import com.blackholecode.saudedigital.common.model.ModelContent

interface Content {

    interface Presenter : BasePresenter {
        fun fetchContent(type: String? = null)
    }

    interface View : BaseView<Presenter> {
        fun displayRequestSuccessful(data: List<ModelContent>)
        fun displayRequestEmptyList()
        fun displayRequestFailure(message: String)
    }

}