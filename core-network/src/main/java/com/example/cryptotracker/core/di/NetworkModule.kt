package com.example.cryptotracker.core.di

import com.example.cryptotracker.core.common.di.CmcApiKey
import com.example.cryptotracker.core.common.di.CmcBaseUrl
import com.example.cryptotracker.core.common.di.IsDebug
import com.example.cryptotracker.core.network.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        @CmcBaseUrl baseUrl: String,
        @CmcApiKey apiKey: String,
        @IsDebug isDebug: Boolean,
    ): Retrofit = RetrofitFactory.create(
        baseUrl = baseUrl,
        apiKey = apiKey,
        isDebug = isDebug
    )
}