package com.blackholecode.saudedigital.content.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.data.ContentRepository

class ContentPresenter(
    private var view: Content.View?,
    private val repository: ContentRepository
) : Content.Presenter {

    override fun fetchContent(type: String?) {
        view?.showProgress(true)

        repository.fetchContent(type, object : RequestCallback<List<ModelContent>> {
            override fun onSuccess(data: List<ModelContent>?) {
                if (data?.isEmpty()!!){
                    view?.displayRequestEmptyList()
                } else {
                    view?.displayRequestSuccessful(data)
                }
            }

            override fun onFailure(message: String?) {
                message?.let { view?.displayRequestFailure(it) }
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