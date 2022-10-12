package com.blackholecode.saudedigital.profile

import com.blackholecode.saudedigital.common.base.BasePresenter
import com.blackholecode.saudedigital.common.base.BaseView
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User

interface Profile {

    interface Presenter: BasePresenter {
        fun fetchProfile()
        fun updateProfile(name: String, age: Int, mOrF: Char, condition: List<Pair<String, String>>)
    }

    interface View : BaseView<Presenter> {
        fun displayFetchSuccess(data: User)
        fun displayFetchFailure(message: String)
    }

}