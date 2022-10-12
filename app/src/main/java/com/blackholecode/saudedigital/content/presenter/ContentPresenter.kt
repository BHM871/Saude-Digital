package com.blackholecode.saudedigital.content.presenter

import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.data.ContentRepository

class ContentPresenter(
    private var view: Content.View?,
    private val repository: ContentRepository
) : Content.Presenter {

    override fun fetchContent(type: Pair<String, String?>?) {
        //TODO("Not yet implemented")
    }

    override fun onDestroy() {
        view = null
    }

}