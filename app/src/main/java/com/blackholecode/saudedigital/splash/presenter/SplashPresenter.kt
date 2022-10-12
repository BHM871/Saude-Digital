package com.blackholecode.saudedigital.splash.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.splash.Splash
import com.blackholecode.saudedigital.splash.data.SplashRepository

class SplashPresenter(
    private var view: Splash.View?,
    private val repository: SplashRepository
) : Splash.Presenter {

    override fun log() {
        repository.log(object : RequestCallback<User> {
            override fun onSuccess(data: User?) {
                view?.onSuccess()
            }

            override fun onFailure(message: String?) {
                view?.onFailure()
            }

            override fun onComplete() {
            }
        })
    }

    override fun onDestroy() {
        view = null
    }

}