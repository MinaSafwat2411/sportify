package com.faswet.sportify.di

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.main.IMainRepository
import com.faswet.sportify.data.repositories.main.MainRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.main.IMainUseCase
import com.faswet.sportify.domain.main.MainUseCase
import com.faswet.sportify.utils.connection.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {
    companion object {
        @Singleton
        @Provides
        fun provideMainRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mILocalDataSource: ILocalDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
        ): IMainRepository {
            return MainRepository(
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource,
            )
        }
    }
    @Singleton
    @Binds
    abstract fun bindIOnMainUseCase(useCase: MainUseCase): IMainUseCase
}