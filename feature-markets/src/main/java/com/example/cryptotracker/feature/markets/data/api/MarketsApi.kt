package com.example.cryptotracker.feature.markets.data.api

import com.example.cryptotracker.feature.markets.data.dto.ListingsLatestResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketsApi {
    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getMarkets(
        @Query("start") start: Int = 1,
        @Query("limit") limit: Int = 100,
        @Query("convert") convert: Int = 100,
    ): ListingsLatestResponse
}