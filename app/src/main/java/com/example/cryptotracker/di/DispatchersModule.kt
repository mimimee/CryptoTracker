package com.example.cryptotracker.di

import com.example.cryptotracker.core.common.AppCoroutineDispatchers
import com.example.cryptotracker.core.common.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatchers(): CoroutineDispatchers =
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            default = Dispatchers.Default,
            main = Dispatchers.Main,
        )
}