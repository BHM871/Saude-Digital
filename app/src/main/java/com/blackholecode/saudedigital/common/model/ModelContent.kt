package com.blackholecode.saudedigital.common.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelContent(
    var id: String? = null,
    var timestamp: Timestamp? = null,
    var thumbnail: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var videoUrl: String? = null,
    var condition: String? = null,
    var type: String? = null
) : Parcelable {

    fun toVideo() : ModelVideo {
        return ModelVideo(
            id = id,
            timestamp = timestamp,
            thumbnail = thumbnail,
            title = title,
            description = description,
            videoUrl = videoUrl,
            condition = condition,
            type = type
        )
    }

    fun toFood() : ModelFood {
        return ModelFood(
            id = id,
            timestamp = timestamp,
            thumbnail = thumbnail,
            title = title,
            description = description,
            condition = condition,
            type = type
        )
    }

}