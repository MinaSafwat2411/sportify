package com.faswet.sportify.data.models.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.util.Date

@Keep
data class ActivityHistory(
    @SerializedName("achievement")
    val achievement: String? = null,
    @SerializedName("date")
    val date: Date? = null,
    @SerializedName("sport")
    val sport: String? = null
)
