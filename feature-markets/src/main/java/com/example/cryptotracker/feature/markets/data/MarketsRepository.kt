package com.example.cryptotracker.feature.markets.data

import com.example.cryptotracker.core.common.AppError
import com.example.cryptotracker.core.common.CoroutineDispatchers
import com.example.cryptotracker.core.common.DefaultCoroutineDispatchers
import com.example.cryptotracker.core.network.RetrofitFactory
import com.example.cryptotracker.core.network.mapThrowableToAppError
import com.example.cryptotracker.feature.markets.data.api.MarketsApi
import com.example.cryptotracker.feature.markets.data.mapper.toDomain
import com.example.cryptotracker.feature.markets.domain.MarketItem
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

interface MarketsRepository {
    suspend fun loadMarkets(): Result<List<MarketItem>>
}

class MarketsRepositoryImpl(
    private val retrofit: Retrofit = RetrofitFactory.create(baseUrl = BASE_URL),
    private val dispatchers: CoroutineDispatchers = DefaultCoroutineDispatchers,
) : MarketsRepository {
    private val api: MarketsApi by lazy { retrofit.create(MarketsApi::class.java) }

    override suspend fun loadMarkets(): Result<List<MarketItem>> = withContext(dispatchers.io) {
        runCatching {
            val response = api.getMarkets()
            response.items.map { dto -> dto.toDomain() }
        }.mapError(::mapThrowableToAppError)
    }

    private inline fun <T> Result<T>.mapError(mapper: (Throwable) -> AppError): Result<T> =
        fold(onSuccess = { Result.success(it) }, onFailure = { Result.failure(mapper(it)) })

    companion object {
        private const val BASE_URL = "https://api.example.com/" // подставим реальный позже
    }
}