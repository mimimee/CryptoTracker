package com.example.cryptotracker.feature.markets.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketsResponse(
    @SerialName("items") val items: List<MarketItemDto>,
)

@Serializable
data class MarketItemDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("price_usd") val priceUsd: String,
    @SerialName("change_24h") val change24h: String,
)