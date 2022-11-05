package com.blackholecode.saudedigital.search.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.search.Search
import com.blackholecode.saudedigital.search.data.SearchRepository

class SearchPresenter(
    private var view: Search.View?,
    private val repository: SearchRepository
) : Search.Presenter {

    override fun searchVideos(text: String?) {
        view?.showProgress(true)
        repository.search(text, object : RequestCallback<List<ModelContent>> {
            override fun onSuccess(data: List<ModelContent>?) {
                if (data == null || data.isEmpty()) {
                    view?.displayRequestEmptyList()
                } else {
                    view?.displayRequestFullList(data)
                }
            }

            override fun onFailure(message: String?) {
                view?.displayRequestFailure(message ?:"Error in serv")
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