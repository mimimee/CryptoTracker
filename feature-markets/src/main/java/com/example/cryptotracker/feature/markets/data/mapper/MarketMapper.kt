package com.example.cryptotracker.feature.markets.data.mapper

import com.example.cryptotracker.feature.markets.data.dto.MarketItemDto
import com.example.cryptotracker.feature.markets.domain.MarketItem

fun MarketItemDto.toDomain() = MarketItem(
    id = id,
    name = name,
    priceUsd = priceUsd,
    change24h = change24h
)