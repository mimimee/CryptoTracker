package com.example.cryptotracker.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @CmcBaseUrl baseUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @CmcApiKey apiKey: String,
        @IsDebug isDebug: Boolean,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(getApiHeadersInterceptor(apiKey))
        .addInterceptor(getLoggingInterceptor(isDebug))
        .build()

    private fun getLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = when {
                isDebug -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun getApiHeadersInterceptor(apiKey: String): Interceptor =
        Interceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader("X-CMC_PRO_API_KEY", apiKey)
                .addHeader("Accept-Encoding", "deflate, gzip")
                .build()

            chain.proceed(request)
        }

    private val json: Json = Json { ignoreUnknownKeys = true }

    private val converterFactory: Converter.Factory =
        json.asConverterFactory("application/json; charset=UTF-8".toMediaType())
}