package com.faswet.sportify.domain.booking

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.booking.BookingResponse
import com.faswet.sportify.data.repositories.booking.IBookingRepository
import com.faswet.sportify.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookingUseCase @Inject constructor(
    private val mBookingRepository: IBookingRepository
): BaseUseCase(mBookingRepository),IBookingUseCase{
    override fun getAllBooking(): Flow<FirebaseResponse<List<BookingResponse>>> {
        return mBookingRepository.getAllBooking()
    }
}