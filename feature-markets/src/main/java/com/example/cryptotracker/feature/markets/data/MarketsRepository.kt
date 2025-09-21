package com.example.cryptotracker.feature.markets.data

import com.example.cryptotracker.core.common.AppError
import com.example.cryptotracker.core.common.CoroutineDispatchers
import com.example.cryptotracker.core.common.DefaultCoroutineDispatchers
import com.example.cryptotracker.core.network.mapThrowableToAppError
import com.example.cryptotracker.feature.markets.data.api.MarketsApi
import com.example.cryptotracker.feature.markets.data.mapper.toDomain
import com.example.cryptotracker.feature.markets.domain.MarketItem
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface MarketsRepository {
    suspend fun loadMarkets(): Result<List<MarketItem>>
}

class MarketsRepositoryImpl @Inject constructor(
    private val api: MarketsApi,
    private val dispatchers: CoroutineDispatchers = DefaultCoroutineDispatchers,
) : MarketsRepository {

    override suspend fun loadMarkets(): Result<List<MarketItem>> = withContext(dispatchers.io) {
        runCatching {
            val response = api.getMarkets()
            response.data.map { dto -> dto.toDomain() }
        }.mapError(::mapThrowableToAppError)
    }

    private inline fun <T> Result<T>.mapError(mapper: (Throwable) -> AppError): Result<T> =
        fold(onSuccess = { Result.success(it) }, onFailure = { Result.failure(mapper(it)) })
}