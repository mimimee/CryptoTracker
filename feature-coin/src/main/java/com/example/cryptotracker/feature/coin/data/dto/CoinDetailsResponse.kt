package com.example.cryptotracker.feature.coin.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailsResponse(
    val status: StatusDto,
    val data: Map<String, CoinDto>,
)

@Serializable
data class StatusDto(
    @SerialName("error_code") val errorCode: Int? = null,
    @SerialName("error_message") val errorMessage: String? = null,
)

@Serializable
data class CoinDto(
    val id: Int,
    val name: String,
    val symbol: String,
    val quote: QuoteContainerDto,
)

@Serializable
data class QuoteContainerDto(
    @SerialName("USD") val usd: UsdQuoteDto,
)

@Serializable
data class UsdQuoteDto(
    val price: Double,
    @SerialName("percent_change_24h") val percentChange24h: Double,
)