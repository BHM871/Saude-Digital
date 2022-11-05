package com.blackholecode.saudedigital.food.presenter

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.food.Food
import com.blackholecode.saudedigital.food.data.FoodRepository

class FoodPresenter(
    private var view: Food.View?,
    private val repository: FoodRepository
) : Food.Presenter {

    override fun searchSimilar(type: String) {
        view?.showProgress(true)

        repository.searchSimilar(type, object : RequestCallback<List<ModelContent>> {
            override fun onSuccess(data: List<ModelContent>?) {
               if (data != null && data.isNotEmpty()) {
                   view?.displayRequestFullList(data)
               } else {
                   view?.displayRequestEmptyList()
               }
            }

            override fun onFailure(message: String?) {
                view?.displayRequestFailure(message ?: "Error in serv")
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