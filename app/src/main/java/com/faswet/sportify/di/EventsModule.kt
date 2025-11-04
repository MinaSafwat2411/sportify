package com.faswet.sportify.di

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.events.EventsRepository
import com.faswet.sportify.data.repositories.events.IEventsRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.events.EventsUseCase
import com.faswet.sportify.domain.events.IEventsUseCase
import com.faswet.sportify.utils.connection.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  EventsModule {
    companion object{
        @Provides
        @Singleton
        fun provideEventsRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
            mILocalDataSource: ILocalDataSource,
        ): IEventsRepository {
            return EventsRepository(
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource,
            )
        }
    }
    @Binds
    abstract fun bindIEventsUseCase(useCase: EventsUseCase): IEventsUseCase
}