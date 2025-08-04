package com.faswet.sportify.di

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.onboarding.IOnboardingRepository
import com.faswet.sportify.data.repositories.onboarding.OnboardingRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.onboarding.IOnBoardingUseCase
import com.faswet.sportify.domain.onboarding.OnBoardingUseCase
import com.faswet.sportify.utils.connection.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class OnBoardingModule {
    companion object {
        @Singleton
        @Provides
        fun provideOnboardingRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mILocalDataSource: ILocalDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
        ): IOnboardingRepository {
            return OnboardingRepository(
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource,
            )
        }
    }
    @Singleton
    @Binds
    abstract fun bindIOnOnboardingUseCase(useCase: OnBoardingUseCase): IOnBoardingUseCase
}