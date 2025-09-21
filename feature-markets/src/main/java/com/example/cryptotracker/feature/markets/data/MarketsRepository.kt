package com.example.cryptotracker.feature.markets.data

import com.example.cryptotracker.core.common.CoroutineDispatchers
import com.example.cryptotracker.core.common.mapError
import com.example.cryptotracker.core.network.mapThrowableToAppError
import com.example.cryptotracker.feature.markets.data.api.MarketsApi
import com.example.cryptotracker.feature.markets.data.mapper.toDomain
import com.example.cryptotracker.feature.markets.domain.MarketItem
import kotlinx.coroutines.withContext

interface MarketsRepository {
    suspend fun loadMarkets(): Result<List<MarketItem>>
}

class MarketsRepositoryImpl @Inject constructor(
    private val api: MarketsApi,
    private val dispatchers: CoroutineDispatchers,
) : MarketsRepository {

    override suspend fun loadMarkets(): Result<List<MarketItem>> = withContext(dispatchers.io) {
        runCatching {
            val response = api.getMarkets()
            response.data.map { dto -> dto.toDomain() }
        }.mapError(::mapThrowableToAppError)
    }
}