package com.blackholecode.saudedigital.main.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.main.Main
import com.blackholecode.saudedigital.main.data.MainRepository

class MainPresenter(
    private var view: Main.View?,
    private val repository: MainRepository
) : Main.Presenter {

    override fun logout() {
        view?.showProgress(true)
        repository.logout(object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.displayLogoutSuccess()
            }

            override fun onFailure(message: String) {
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