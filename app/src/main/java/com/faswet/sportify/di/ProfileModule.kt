package com.faswet.sportify.di

import com.faswet.sportify.data.drive.IGoogleDriveUploader
import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.profile.IProfileRepository
import com.faswet.sportify.data.repositories.profile.ProfileRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.profile.IProfileUseCase
import com.faswet.sportify.domain.profile.ProfileUseCase
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
abstract class  ProfileModule {
    companion object{
        @Provides
        @Singleton
        fun provideProfileRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
            mILocalDataSource: ILocalDataSource,
            mFirebaseService: IFirebaseService,
            mGoogleDriveUploader: IGoogleDriveUploader,
            ): IProfileRepository {
            return ProfileRepository(
                connectionUtils,
                mIRemoteDataSource,
                mILocalDataSource,
                mIPreferencesDataSource,
                mGoogleDriveUploader,
            )
        }
    }
    @Binds
    abstract fun bindIProfileUseCase(useCase: ProfileUseCase): IProfileUseCase
}