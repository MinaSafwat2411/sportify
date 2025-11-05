package com.faswet.sportify.domain.booking

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.booking.BookingResponse
import com.faswet.sportify.domain.base.IBaseUseCase
import kotlinx.coroutines.flow.Flow

interface IBookingUseCase : IBaseUseCase {

    fun getAllBooking(): Flow<FirebaseResponse<List<BookingResponse>>>
}