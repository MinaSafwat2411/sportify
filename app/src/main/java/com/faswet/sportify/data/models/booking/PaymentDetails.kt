package com.faswet.sportify.data.models.booking

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PaymentDetails(
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("transactionID")
    val transactionID: String? = null,
    @SerializedName("transactionType")
    val transactionType: String? = null
)
