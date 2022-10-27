package com.blackholecode.saudedigital.common.base

import com.blackholecode.saudedigital.common.util.UserSession
import com.blackholecode.saudedigital.common.view.information.Information
import com.blackholecode.saudedigital.common.view.information.data.InformationDataSourceFactory
import com.blackholecode.saudedigital.common.view.information.data.InformationFireDataSource
import com.blackholecode.saudedigital.common.view.information.data.InformationLocalDataSource
import com.blackholecode.saudedigital.common.view.information.data.InformationRepository
import com.blackholecode.saudedigital.common.view.information.presenter.InformationPresenter
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.data.ContentFireDataSource
import com.blackholecode.saudedigital.content.data.ContentLocalDataSource
import com.blackholecode.saudedigital.content.data.ContentRepository
import com.blackholecode.saudedigital.content.presenter.ContentPresenter
import com.blackholecode.saudedigital.login.Login
import com.blackholecode.saudedigital.login.data.LoginFireDataSource
import com.blackholecode.saudedigital.login.data.LoginRepository
import com.blackholecode.saudedigital.login.presenter.LoginPresenter
import com.blackholecode.saudedigital.main.Main
import com.blackholecode.saudedigital.main.data.MainFireDataSource
import com.blackholecode.saudedigital.main.data.MainLocalDataSource
import com.blackholecode.saudedigital.main.data.MainRepository
import com.blackholecode.saudedigital.main.presenter.MainPresenter
import com.blackholecode.saudedigital.profile.Profile
import com.blackholecode.saudedigital.profile.data.ProfileDataSourceFactory
import com.blackholecode.saudedigital.profile.data.ProfileRepository
import com.blackholecode.saudedigital.profile.presenter.ProfilePresenter
import com.blackholecode.saudedigital.register.RegisterEmailAndPassword
import com.blackholecode.saudedigital.register.RegisterPhoto
import com.blackholecode.saudedigital.register.data.RegisterFireDataSource
import com.blackholecode.saudedigital.register.data.RegisterRepository
import com.blackholecode.saudedigital.register.presenter.RegisterEmailAndPasswordPresenter
import com.blackholecode.saudedigital.register.presenter.RegisterPhotoPresenter
import com.blackholecode.saudedigital.search.Search
import com.blackholecode.saudedigital.search.presenter.SearchPresenter
import com.blackholecode.saudedigital.splash.Splash
import com.blackholecode.saudedigital.splash.data.SplashFireDataSource
import com.blackholecode.saudedigital.splash.data.SplashRepository
import com.blackholecode.saudedigital.splash.presenter.SplashPresenter

object DependencyInjector {

    private fun loginRepository() : LoginRepository {
        return LoginRepository(LoginFireDataSource())
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
        return RegisterEmailAndPasswordPresenter(view, registerRepository())
    }

    fun registerPhotoPresenter(view: RegisterPhoto.View): RegisterPhoto.Presenter {
        return RegisterPhotoPresenter(view, registerRepository())
    }

    private fun informationRepository(): InformationRepository {
        return InformationRepository(InformationDataSourceFactory(UserSession))
    }

    fun informationPresenter(view: Information.View): Information.Presenter {
        return InformationPresenter(view, informationRepository())
    }

    private fun contentRepository() : ContentRepository {
        return ContentRepository(ContentLocalDataSource(UserSession), ContentFireDataSource())
    }

    fun contentPresenter(view: Content.View) : Content.Presenter {
        return ContentPresenter(view, contentRepository())
    }

    private fun splashRepository() : SplashRepository {
        return SplashRepository(SplashFireDataSource())
    }

    fun splashPresenter(view: Splash.View): Splash.Presenter {
        return SplashPresenter(view, splashRepository())
    }

    private fun mainRepository() : MainRepository {
        return MainRepository(MainLocalDataSource(), MainFireDataSource())
    }

    fun mainPresenter(view: Main.View): Main.Presenter {
        return MainPresenter(view, mainRepository())
    }

    fun searchPresenter(view: Search.View): Search.Presenter {
        return SearchPresenter(view)
    }

}