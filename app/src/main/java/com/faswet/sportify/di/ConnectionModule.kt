package com.faswet.sportify.di

import com.faswet.sportify.utils.connection.ConnectionUtils
import com.faswet.sportify.utils.connection.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectionModule {

    @Binds
    @Singleton
    abstract fun bindConnectionUtils(
        impl: ConnectionUtils
    ): IConnectionUtils
}