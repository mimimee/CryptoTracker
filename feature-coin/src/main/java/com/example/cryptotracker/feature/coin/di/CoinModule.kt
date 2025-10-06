package com.example.cryptotracker.feature.coin.di

import com.example.cryptotracker.feature.coin.data.CoinRepository
import com.example.cryptotracker.feature.coin.data.CoinRepositoryImpl
import com.example.cryptotracker.feature.coin.data.api.CoinApi
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
object CoinApiModule {
    @Provides
    @Singleton
    fun provideCoinAPi(retrofit: Retrofit): CoinApi =
        retrofit.create<CoinApi>()
}

@Module
@InstallIn(SingletonComponent::class)
interface CoinRepositoryModule {
    @Binds
    @Singleton
    fun bindCoinRepo(impl: CoinRepositoryImpl): CoinRepository
}