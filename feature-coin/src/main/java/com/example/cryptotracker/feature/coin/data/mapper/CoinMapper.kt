package com.example.cryptotracker.feature.coin.data.mapper

import com.example.cryptotracker.feature.coin.data.dto.CoinDto
import com.example.cryptotracker.feature.coin.domain.CoinDetails

fun Map.Entry<String, CoinDto>.toDomain(): CoinDetails {
    val dto = value
    return CoinDetails(
        id = key,
        name = dto.name,
        symbol = dto.symbol,
        priceUsd = dto.quote.usd.price,
        change24h = dto.quote.usd.percentChange24h
    )
}