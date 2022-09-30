package com.blackholecode.saudedigital.common.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val id: String,
    @DrawableRes var thumbnail: Int,
    var title: String,
    var description: String = title,
    val type: String = "Food"
) : Parcelable