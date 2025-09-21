package com.example.cryptotracker.feature.markets.data.mapper

import com.example.cryptotracker.feature.markets.data.dto.CoinDto
import com.example.cryptotracker.feature.markets.domain.MarketItem

fun CoinDto.toDomain() = MarketItem(
    id = id.toString(),
    name = name,
    symbol = symbol,
    priceUsd = quote.usd.price,
    change24h = quote.usd.percentChange24h
)