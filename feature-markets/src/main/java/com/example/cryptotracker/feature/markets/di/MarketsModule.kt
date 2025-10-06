package com.example.cryptotracker.feature.markets.di

import com.example.cryptotracker.feature.markets.data.MarketsRepository
import com.example.cryptotracker.feature.markets.data.MarketsRepositoryImpl
import com.example.cryptotracker.feature.markets.data.api.MarketsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarketsApiModule {
    @Provides
    @Singleton
    fun provideMarketsAPi(retrofit: Retrofit): MarketsApi =
        retrofit.create<MarketsApi>()
}

@Module
@InstallIn(SingletonComponent::class)
interface MarketsRepositoryModule {
    @Binds
    @Singleton
    fun bindMarketsRepo(impl: MarketsRepositoryImpl): MarketsRepository
}

