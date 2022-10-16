package com.blackholecode.saudedigital.search.presenter

import android.os.Looper
import com.blackholecode.saudedigital.search.Search
import android.os.Handler

class SearchPresenter(
    private var view: Search.View?
) : Search.Presenter {

    override fun searchVideos(title: String?) {
        view?.showProgress(true)
        Handler(Looper.getMainLooper()).postDelayed({
            view?.showProgress(false)
        }, 500)
    }

    override fun onDestroy() {
        view = null
    }
}