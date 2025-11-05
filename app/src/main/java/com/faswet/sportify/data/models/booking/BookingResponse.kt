package com.faswet.sportify.data.models.booking

import com.google.firebase.Timestamp

data class BookingResponse(
    val bookingStatus: BookingStatus? = null,
    val courtId: String? = null,
    val endTime: String? = null,
    val payment: PaymentStatus? = null,
    val paymentDetails: PaymentDetails? = null,
    val sport: String? = null,
    val startTime: String? = null,
    val userId: String? = null
)
