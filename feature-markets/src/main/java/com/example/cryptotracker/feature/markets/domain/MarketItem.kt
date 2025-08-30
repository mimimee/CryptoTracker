package com.example.cryptotracker.feature.markets.domain

data class MarketItem(
    val id: String,
    val name: String,
    val priceUsd: String,
    val change24h: String,
)