package com.faswet.sportify.data.models.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProfilePicture(
    @SerializedName("isUploaded")
    val isUploaded: Boolean? = null,
    @SerializedName("profileId")
    val profileId: Int? = null,
    @SerializedName("profileUrl")
    val profileUrl: String? = null
)
