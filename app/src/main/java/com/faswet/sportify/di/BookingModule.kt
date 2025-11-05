package com.faswet.sportify.di

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.booking.BookingRepository
import com.faswet.sportify.data.repositories.booking.IBookingRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.booking.BookingUseCase
import com.faswet.sportify.domain.booking.IBookingUseCase
import com.faswet.sportify.utils.connection.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  BookingModule {
    companion object{
        @Provides
        @Singleton
        fun provideBookingRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
            mILocalDataSource: ILocalDataSource,
        ): IBookingRepository {
            return BookingRepository(
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource,
            )
        }
    }
    @Binds
    abstract fun bindIBookingUseCase(useCase: BookingUseCase): IBookingUseCase
}