package com.example.cryptotracker.feature.markets.data

import com.example.cryptotracker.feature.markets.domain.MarketItem
import kotlinx.coroutines.delay
import kotlin.random.Random

interface MarketsRepository {
    suspend fun loadMarkets(): Result<List<MarketItem>>
}

class MarketsRepositoryFake : MarketsRepository {
    override suspend fun loadMarkets(): Result<List<MarketItem>> {
        delay(500)
        if (Random.nextFloat() < 0.1f) {
            return Result.failure(IllegalStateException("Network Error"))
        }

        val list = listOf(
            MarketItem("BTC", "Bitcoin", "64 102.12", "+1.3%"),
            MarketItem("ETH", "Ethereum", "3 121.55", "-0.6%"),
            MarketItem("SOL", "Solana", "168.42", "+4.1%"),
            MarketItem("DOGE", "Dogecoin", "0.124", "+0.2%"),
        )
        return Result.success(list)
    }
}