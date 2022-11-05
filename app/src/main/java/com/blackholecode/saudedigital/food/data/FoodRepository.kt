package com.blackholecode.saudedigital.food.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent

class FoodRepository(
    private val remoteDataSource: FoodDataSource
) {

    fun searchSimilar(type: String, callback: RequestCallback<List<ModelContent>>) {
        remoteDataSource.searchSimilar(type, callback)
    }

}