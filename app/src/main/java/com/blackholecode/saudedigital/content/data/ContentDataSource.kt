package com.blackholecode.saudedigital.content.data

import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.MContent

interface ContentDataSource {

    fun fetchContent(type: Pair<String, String>, callback: RequestCallback<List<MContent>>)

}