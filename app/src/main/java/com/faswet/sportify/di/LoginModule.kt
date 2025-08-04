package com.faswet.sportify.di

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.login.ILoginRepository
import com.faswet.sportify.data.repositories.login.LoginRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.domain.login.ILoginUseCase
import com.faswet.sportify.domain.login.LoginUseCase
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
abstract class  LoginModule {
    companion object{
        @Provides
        @Singleton
        fun provideLoginRepository(
            connectionUtils: IConnectionUtils,
            mIRemoteDataSource: IRemoteDataSource,
            mIPreferencesDataSource: IPreferencesDataSource,
            mFirebaseService: FirebaseService
        ): ILoginRepository {
            return LoginRepository(
                connectionUtils,
                mIRemoteDataSource,
                mIPreferencesDataSource,
                mFirebaseService
            )
        }
    }
    @Binds
    abstract fun bindILoginUseCase(useCase: LoginUseCase): ILoginUseCase
}