package com.example.cryptotracker.feature.markets.domain

data class MarketItem(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val change24h: Double,
)