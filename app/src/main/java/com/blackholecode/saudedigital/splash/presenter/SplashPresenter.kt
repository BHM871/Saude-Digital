package com.blackholecode.saudedigital.splash.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.splash.Splash
import com.blackholecode.saudedigital.splash.data.SplashRepository

class SplashPresenter(
    private var view: Splash.View?,
    private val repository: SplashRepository
) : Splash.Presenter {

    override fun log() {
        repository.log(object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.goToMainScreen()
            }

            override fun onFailure(message: String) {
                view?.goToLoginScreen()
            }

            override fun onComplete() {
            }
        })
    }

    override fun onDestroy() {
        view = null
    }

}