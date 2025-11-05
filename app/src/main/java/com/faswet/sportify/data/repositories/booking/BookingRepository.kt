package com.faswet.sportify.data.repositories.booking

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.booking.BookingResponse
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.base.BaseRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.di.IoDispatcher
import com.faswet.sportify.utils.connection.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookingRepository @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    private val mILocalDataSource: ILocalDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : BaseRepository(connectionUtils, mIRemoteDataSource, mIPreferencesDataSource, dispatcher),
    IBookingRepository {
    override fun getAllBooking(): Flow<FirebaseResponse<List<BookingResponse>>> {
        return mIRemoteDataSource.getAllBooking()
    }
}