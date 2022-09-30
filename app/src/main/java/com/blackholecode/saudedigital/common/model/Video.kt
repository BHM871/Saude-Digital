package com.blackholecode.saudedigital.common.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val id: String?,
    @DrawableRes var thumbnail: Int,
    var title: String,
    var description: String = title,
    var duration: String = "0:0",
    val type: String
) : Parcelable