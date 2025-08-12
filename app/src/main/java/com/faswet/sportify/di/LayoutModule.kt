package com.faswet.sportify.di

import com.faswet.sportify.data.drive.GoogleDriveUploader
import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.layout.ILayoutRepository
import com.faswet.sportify.data.repositories.layout.LayoutRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.layout.ILayoutUseCase
import com.faswet.sportify.domain.layout.LayoutUseCase
import com.faswet.sportify.firebase.FirebaseService
import com.faswet.sportify.utils.connection.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  LayoutModule {
    companion object{
        @Provides
        @Singleton
        fun provideLayoutRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
            mILocalDataSource: ILocalDataSource,
            mFirebaseService: FirebaseService,
            mGoogleDriveUploader: GoogleDriveUploader
        ): ILayoutRepository {
            return LayoutRepository(
                mILocalDataSource,
                mIRemoteDataSource,
                mIPreferencesDataSource,
                mGoogleDriveUploader,
                mFirebaseService
            )
        }
    }
    @Binds
    abstract fun bindILayoutUseCase(useCase: LayoutUseCase): ILayoutUseCase
}