package com.faswet.sportify.data.models.events

import androidx.annotation.Keep
import com.google.firebase.firestore.GeoPoint
import com.google.gson.annotations.SerializedName
import java.util.Date

@Keep
data class EventResponse(
    @SerializedName("date")
    val date: Date? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("location")
    val location: GeoPoint? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("timeFrom")
    val timeFrom: String? = null,
    @SerializedName("timeTo")
    val timeTo: String? = null
)
