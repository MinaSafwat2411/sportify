package com.faswet.sportify.data.models.booking

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PaymentStatus(
    @SerializedName("paymentId")
    val paymentId: Int? = null,
    @SerializedName("paymentStatus")
    val paymentStatus: String? = null
)
