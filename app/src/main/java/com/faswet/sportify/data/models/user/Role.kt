package com.faswet.sportify.data.models.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Role(
    @SerializedName("roleName")
    val roleName: String? = null,
    @SerializedName("roleValue")
    val roleValue: Int?= null,
)
