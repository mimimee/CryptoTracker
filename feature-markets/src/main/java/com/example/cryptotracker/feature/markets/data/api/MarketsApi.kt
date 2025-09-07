package com.example.cryptotracker.feature.markets.data.api

import com.example.cryptotracker.feature.markets.data.dto.MarketsResponse
import retrofit2.http.GET

interface MarketsApi {
    @GET("markets")
    suspend fun getMarkets(): MarketsResponse
}