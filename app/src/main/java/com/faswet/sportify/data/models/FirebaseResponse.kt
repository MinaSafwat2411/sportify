package com.faswet.sportify.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FirebaseResponse<T>(
    @SerializedName("status")
    @Expose
    var status: Boolean = false,
    @SerializedName("status")
    @Expose
    var message: String? = null,
    @SerializedName("status")
    @Expose
    var data: T? = null,
)

