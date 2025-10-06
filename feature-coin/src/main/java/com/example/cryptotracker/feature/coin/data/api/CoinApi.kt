package com.example.cryptotracker.feature.coin.data.api

import com.example.cryptotracker.feature.coin.data.dto.CoinDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("v2/cryptocurrency/quotes/latest")
    suspend fun getCoinDetails(@Query("id") id: String): CoinDetailsResponse
}