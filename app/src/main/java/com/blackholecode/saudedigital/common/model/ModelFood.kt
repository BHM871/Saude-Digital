package com.blackholecode.saudedigital.common.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelFood(
    val id: String? = null,
    var timestamp: Timestamp? = null,
    @DrawableRes var thumbnail: Int? = null,
    var title: String? = null,
    var description: String? = null,
    val condition: String? = null,
    val type: String? = null
) : Parcelable