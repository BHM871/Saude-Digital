package com.blackholecode.saudedigital.food.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent

interface FoodDataSource {

    fun searchSimilar(type: String, callback: RequestCallback<List<ModelContent>>)

}