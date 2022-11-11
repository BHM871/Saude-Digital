package com.blackholecode.saudedigital.register.presenter

import android.net.Uri
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.register.RegisterPhoto
import com.blackholecode.saudedigital.register.data.RegisterRepository

class RegisterPhotoPresenter(
    var view: RegisterPhoto.View? = null,
    val repository: RegisterRepository
) : RegisterPhoto.Presenter {

    override fun update(photoUri: Uri) {
        view?.showProgress(true)

        repository.updateUser(photoUri, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.displayUpdateSuccess()
            }

            override fun onFailure(message: String) {
                view?.displayUpdateFailure(message)
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