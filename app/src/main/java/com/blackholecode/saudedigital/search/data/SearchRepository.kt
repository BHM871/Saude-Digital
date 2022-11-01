package com.blackholecode.saudedigital.search.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.ModelContent

class SearchRepository(
    private val localDataSource: SearchDataSource,
    private val remoteDataSource: SearchDataSource
) {

    fun search(text: String?, callback: RequestCallback<List<ModelContent>>) {
        if (text == null || text == "" || text.isEmpty()){

            val list = localDataSource.getCache()
            callback.onSuccess(list)
            callback.onComplete()

        } else {

            remoteDataSource.search(text, object : RequestCallback<List<ModelContent>> {
                override fun onSuccess(data: List<ModelContent>?) {
                    localDataSource.putCache(data!!)
                    callback.onSuccess(data)
                }

                override fun onFailure(message: String?) {
                    callback.onFailure(message)
                }

                override fun onComplete() {
                    callback.onComplete()
                }
            })
        }

    }

}