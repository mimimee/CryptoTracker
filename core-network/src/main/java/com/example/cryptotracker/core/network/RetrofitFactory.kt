package com.example.cryptotracker.core.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    fun create(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(getConverterFactory())
            .build()

    private fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor(true))
            .build()

    private fun getLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = when {
                isDebug -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.BASIC
            }
        }

    private fun getConverterFactory(): Converter.Factory =
        Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
}