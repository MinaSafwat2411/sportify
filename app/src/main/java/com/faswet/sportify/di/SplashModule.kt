package com.faswet.sportify.di

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.splash.ISplashRepository
import com.faswet.sportify.data.repositories.splash.SplashRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.splash.ISplashUseCase
import com.faswet.sportify.domain.splash.SplashUseCase
import com.faswet.sportify.firebase.IFirebaseService
import com.faswet.sportify.utils.connection.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class SplashModule {
    companion object {
        @Singleton
        @Provides
        fun provideSplashRepository(
            mFirebaseService: IFirebaseService,
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mILocalDataSource: ILocalDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
        ): ISplashRepository {
            return SplashRepository(
                mFirebaseService,
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource,
            )
        }
    }
    @Singleton
    @Binds
    abstract fun bindIOnSplashUseCase(useCase: SplashUseCase): ISplashUseCase
}