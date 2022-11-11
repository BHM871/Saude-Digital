package com.blackholecode.saudedigital.common.util.information.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.Condition
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
        sex: Int,
        condition: List<Condition<Int?, Int?>?>,
    ) {
        view?.showProgress(true)

        repository.create(email, password, name, age, sex, condition, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.displaySuccessCreate()
            }

            override fun onFailure(message: String) {
                view?.displayFailureCreate(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun updateProfile(
        name: String,
        age: Int,
        sex: Int,
        condition: List<Condition<Int?, Int?>?>,
    ) {
        view?.showProgress(true)

        repository.updateProfile(name, age, sex, condition, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.displaySuccessUpdate()
            }

            override fun onFailure(message: String) {
                view?.displayFailureCreate(message)
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

            override fun onFailure(message: String) {
                view?.displayFailureFetch(message)
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