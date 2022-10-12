package com.blackholecode.saudedigital.content.data

class ContentDataSourceFactory {

    fun createLocalDataSource() : ContentDataSource {
        return ContentLocalDataSource()
    }

    fun createRemoteDataSource() : ContentDataSource {
        return ContentFireDataSource()
    }

}