package com.faswet.sportify.data.models.booking

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BookingStatus(
    @SerializedName("bookingStatusId")
    val bookingStatusId: Int? = null,
    @SerializedName("bookingStatusValue")
    val bookingStatusValue: String? = null
)
