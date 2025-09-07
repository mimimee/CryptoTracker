package com.example.cryptotracker.core.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface RawService {
    @GET
    suspend fun getRaw(@Url fullUrl: String): ResponseBody
}