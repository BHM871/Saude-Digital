package com.blackholecode.saudedigital.common.base

import com.blackholecode.saudedigital.common.util.UserSession
import com.blackholecode.saudedigital.common.util.information.Information
import com.blackholecode.saudedigital.common.util.information.data.InformationDataSourceFactory
import com.blackholecode.saudedigital.common.util.information.data.InformationRepository
import com.blackholecode.saudedigital.common.util.information.presenter.InformationPresenter
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.data.ContentDataSourceFactory
import com.blackholecode.saudedigital.content.data.ContentRepository
import com.blackholecode.saudedigital.content.presenter.ContentPresenter
import com.blackholecode.saudedigital.login.Login
import com.blackholecode.saudedigital.login.data.LoginDataSourceFactory
import com.blackholecode.saudedigital.login.data.LoginFireDataSource
import com.blackholecode.saudedigital.login.data.LoginRepository
import com.blackholecode.saudedigital.login.presenter.LoginPresenter
import com.blackholecode.saudedigital.profile.Profile
import com.blackholecode.saudedigital.profile.data.ProfileDataSourceFactory
import com.blackholecode.saudedigital.profile.data.ProfileRepository
import com.blackholecode.saudedigital.profile.presenter.ProfilePresenter
import com.blackholecode.saudedigital.register.RegisterEmailAndPassword
import com.blackholecode.saudedigital.register.data.RegisterFireDataSource
import com.blackholecode.saudedigital.register.data.RegisterRepository
import com.blackholecode.saudedigital.register.presenter.FragmentRegisterEmailAndPasswordPresenter
import com.blackholecode.saudedigital.splash.Splash
import com.blackholecode.saudedigital.splash.data.SplashDataSourceFactory
import com.blackholecode.saudedigital.splash.data.SplashRepository
import com.blackholecode.saudedigital.splash.presenter.SplashPresenter

object DependencyInjector {

    private fun loginRepository() : LoginRepository {
        return LoginRepository(LoginDataSourceFactory(UserSession))
    }

    fun loginPresenter(view: Login.View) : Login.Presenter {
        return LoginPresenter(view, loginRepository())
    }

    private fun profileRepository() : ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(UserSession))
    }

    fun profilePresenter(view: Profile.View) : Profile.Presenter {
        return ProfilePresenter(view, profileRepository())
    }

    private fun registerRepository() : RegisterRepository {
        return RegisterRepository(RegisterFireDataSource())
    }

    fun registerEmailAndPasswordPresenter(view: RegisterEmailAndPassword.View) : RegisterEmailAndPassword.Presenter {
        return FragmentRegisterEmailAndPasswordPresenter(view, registerRepository())
    }

    private fun informationRepository(): InformationRepository {
        return InformationRepository(InformationDataSourceFactory(UserSession))
    }

    fun informationPresenter(view: Information.View): Information.Presenter {
        return InformationPresenter(view, informationRepository())
    }

    private fun contentRepository() : ContentRepository {
        return ContentRepository(ContentDataSourceFactory())
    }

    fun contentPresenter(view: Content.View) : Content.Presenter {
        return ContentPresenter(view, contentRepository())
    }

    private fun splashRepository() : SplashRepository {
        return SplashRepository(SplashDataSourceFactory(UserSession))
    }

    fun splashPresenter(view: Splash.View): Splash.Presenter {
        return SplashPresenter(view, splashRepository())
    }

}