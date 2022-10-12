package com.blackholecode.saudedigital.profile.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.profile.Profile
import com.blackholecode.saudedigital.profile.data.ProfileRepository

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override fun fetchProfile() {
        view?.showProgress(true)
        repository.fetchProfile(object : RequestCallback<User> {
            override fun onSuccess(data: User?) {
                data?.let { view?.displayFetchSuccess(it) }
            }

            override fun onFailure(message: String?) {
                message?.let { view?.displayFetchFailure(it) }
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