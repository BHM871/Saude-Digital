package com.blackholecode.saudedigital.register.presenter

import android.util.Patterns
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.register.RegisterEmailAndPassword
import com.blackholecode.saudedigital.register.data.RegisterRepository

class FragmentRegisterEmailAndPasswordPresenter(
    private var view: RegisterEmailAndPassword.View?,
    private val repository: RegisterRepository
) : RegisterEmailAndPassword.Presenter {

    override fun create(email: String, password: String, confirmPassword: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 6
        val isConfirmPasswordValid = password.length >= 6

        if (!isEmailValid) view?.displayEmailFailure(R.string.email_invalid)
        else view?.displayEmailFailure(null)

        if (!isPasswordValid) view?.displayEmailFailure(R.string.password_invalid)
        else view?.displayPasswordFailure(null)

        if (!isConfirmPasswordValid) view?.displayPasswordFailure(R.string.password_invalid)
        else view?.displayPasswordFailure(null)

        if (password != confirmPassword) view?.displayPasswordFailure(R.string.password_different)
        else view?.displayPasswordFailure(null)

        if (isEmailValid && isPasswordValid && password == confirmPassword) {
            view?.showProgress(true)
            repository.create(email, password, object : RequestCallback<Boolean> {
                override fun onSuccess(data: Boolean?) {
                    view?.displaySuccessCreate(email, password)
                }

                override fun onFailure(message: String?) {
                    message?.let { view?.displayFailureCreate(it) }
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