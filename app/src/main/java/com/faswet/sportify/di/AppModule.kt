package com.faswet.sportify.di

import android.content.Context
import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.local.LocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.remote.RemoteDataSource
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.data.sharedprefrences.PreferencesDataSource
import com.faswet.sportify.firebase.IFirebaseService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(): ILocalDataSource {
        return LocalDataSource()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(firebaseService: IFirebaseService): IRemoteDataSource {
        return RemoteDataSource(firebaseService)
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(
        @ApplicationContext context: Context,
        gson: Gson
    ): IPreferencesDataSource {
        return PreferencesDataSource(context, gson)
    }

    @Provides
    @IoDispatcher
    fun provideDispatcher(
    ): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(
    ): CoroutineDispatcher {
        return Dispatchers.Main
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher