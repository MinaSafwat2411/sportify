package com.faswet.sportify.data.repositories.booking

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.booking.BookingResponse
import com.faswet.sportify.data.repositories.base.IBaseRepository
import kotlinx.coroutines.flow.Flow

interface IBookingRepository : IBaseRepository {

    fun getAllBooking(): Flow<FirebaseResponse<List<BookingResponse>>>

}