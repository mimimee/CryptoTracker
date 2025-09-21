package com.example.cryptotracker.di

import com.example.cryptotracker.BuildConfig
import com.example.cryptotracker.core.common.di.CmcApiKey
import com.example.cryptotracker.core.common.di.CmcBaseUrl
import com.example.cryptotracker.core.common.di.IsDebug
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {
    @Provides
    @Singleton
    @CmcBaseUrl
    fun provideBaseUrl(): String = BuildConfig.CMC_BASE_URL

    @Provides
    @Singleton
    @CmcApiKey
    fun provideApiKey(): String = BuildConfig.CMC_API_KEY

    @Provides
    @Singleton
    @IsDebug
    fun provideIsDebug(): Boolean = BuildConfig.DEBUG
}