package com.blackholecode.saudedigital.login.presenter

import android.util.Patterns
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.login.Login
import com.blackholecode.saudedigital.login.data.LoginRepository

class LoginPresenter(
    private var view: Login.View?,
    private var repository: LoginRepository
) : Login.Presenter {

    override fun login(email: String, password: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 6

        if (!isEmailValid) view?.displayEmailFailure(R.string.email_invalid)
        else view?.displayEmailFailure(null)

        if (!isPasswordValid) view?.displayEmailFailure(R.string.password_invalid)
        else view?.displayPasswordFailure(null)

        if (isEmailValid && isPasswordValid) {
            view?.showProgress(true)

            repository.login(email, password, object : RequestCallback<Boolean> {
                override fun onSuccess(data: Boolean?) {
                    view?.onUserAuthenticate()
                }

                override fun onFailure(message: String) {
                    view?.onUserUnauthorized(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }
    }

    override fun onDestroy() {
        view = null
    }
}