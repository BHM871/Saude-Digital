package com.blackholecode.saudedigital.common.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class MContent(
    var id: String? = null,
    var thumbnail: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var videoUrl: String? = null,
    var type: String? = null
) : Parcelable {

    fun toVideo() : Video {
        return Video(
            id = id,
            thumbnail = thumbnail,
            title = title,
            description = description,
            videoUrl = videoUrl,
            type = type
        )
    }

    fun toFood() : Food {
        return Food(
            id = id,
            thumbnail = thumbnail,
            title = title,
            description = description,
            type = type
        )
    }

}