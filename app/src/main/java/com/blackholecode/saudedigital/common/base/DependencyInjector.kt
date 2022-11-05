package com.blackholecode.saudedigital.common.base

import com.blackholecode.saudedigital.common.util.UserSession
import com.blackholecode.saudedigital.common.view.information.Information
import com.blackholecode.saudedigital.common.view.information.data.InformationDataSourceFactory
import com.blackholecode.saudedigital.common.view.information.data.InformationRepository
import com.blackholecode.saudedigital.common.view.information.presenter.InformationPresenter
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.data.ContentFireDataSource
import com.blackholecode.saudedigital.content.data.ContentLocalDataSource
import com.blackholecode.saudedigital.content.data.ContentRepository
import com.blackholecode.saudedigital.content.presenter.ContentPresenter
import com.blackholecode.saudedigital.food.Food
import com.blackholecode.saudedigital.food.data.FoodFireDataSource
import com.blackholecode.saudedigital.food.data.FoodRepository
import com.blackholecode.saudedigital.food.presenter.FoodPresenter
import com.blackholecode.saudedigital.login.Login
import com.blackholecode.saudedigital.login.data.LoginFireDataSource
import com.blackholecode.saudedigital.login.data.LoginRepository
import com.blackholecode.saudedigital.login.presenter.LoginPresenter
import com.blackholecode.saudedigital.main.Main
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
import com.blackholecode.saudedigital.search.data.SearchFireDataSource
import com.blackholecode.saudedigital.search.data.SearchLocalDataSource
import com.blackholecode.saudedigital.search.data.SearchRepository
import com.blackholecode.saudedigital.search.presenter.SearchPresenter
import com.blackholecode.saudedigital.search.util.ListSearchCache
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
        return MainRepository(MainLocalDataSource())
    }

    fun mainPresenter(view: Main.View): Main.Presenter {
        return MainPresenter(view, mainRepository())
    }

    private fun searchRepository() : SearchRepository {
        return SearchRepository(SearchLocalDataSource(ListSearchCache), SearchFireDataSource())
    }

    fun searchPresenter(view: Search.View): Search.Presenter {
        return SearchPresenter(view, searchRepository())
    }

    private fun foodRepository() : FoodRepository {
        return FoodRepository(FoodFireDataSource())
    }

    fun foodPresenter(view: Food.View): Food.Presenter {
        return FoodPresenter(view, foodRepository())
    }

}