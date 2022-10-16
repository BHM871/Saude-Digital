package com.blackholecode.saudedigital.common.view.information.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.UserSession
import com.blackholecode.saudedigital.common.view.information.Information
import com.blackholecode.saudedigital.common.view.information.data.InformationRepository

class InformationPresenter(
    private var view: Information.View?,
    private val repository: InformationRepository
) : Information.Presenter {

    override fun create(
        email: String,
        password: String,
        name: String,
        age: Int,
        mOrF: String,
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

    override fun updateProfile(
        name: String,
        age: Int,
        mOrF: String,
        condition: List<Pair<String, String>>
    ) {
        view?.showProgress(true)

        repository.updateProfile(name, age, mOrF, condition, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.displaySuccessUpdate()
            }

            override fun onFailure(message: String?) {
                message?.let { view?.displayFailureUpdate(it) }
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun fetchUser() {
        view?.showProgress(true)

        repository.fetchUser(object : RequestCallback<User> {
            override fun onSuccess(data: User?) {
                if (data != null) view?.displaySuccessFetch(data)
                else view?.displayFailureFetch("User not found")

            }

            override fun onFailure(message: String?) {
                view?.displayFailureFetch(message ?: "User not found")
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