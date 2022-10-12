package com.blackholecode.saudedigital.common.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    var id: String? = null,
    var thumbnail: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var videoUrl: String? = null,
    var type: String? = null
) : Parcelable