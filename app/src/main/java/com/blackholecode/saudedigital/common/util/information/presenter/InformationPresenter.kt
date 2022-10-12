package com.blackholecode.saudedigital.common.util.information.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.util.information.Information
import com.blackholecode.saudedigital.common.util.information.data.InformationRepository

class InformationPresenter(
    private var view: Information.View?,
    private val repository: InformationRepository
) : Information.Presenter {

    override fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: Char,
        condition: List<Pair<String, String>>
    ) {
        view?.showProgress(true)

        repository.create(email, password, name, age, mOrF, condition, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.displaySuccessCreate()
            }

            override fun onFailure(message: String?) {
                message?.let { view?.displayFailureCreate(it) }
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}