package com.example.cryptotracker.feature.coin.data

import com.example.cryptotracker.core.common.CoroutineDispatchers
import com.example.cryptotracker.core.common.mapError
import com.example.cryptotracker.core.network.mapThrowableToAppError
import com.example.cryptotracker.feature.coin.data.api.CoinApi
import com.example.cryptotracker.feature.coin.data.mapper.toDomain
import com.example.cryptotracker.feature.coin.domain.CoinDetails
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CoinRepository {
    suspend fun getCoinDetails(id: String): Result<CoinDetails>
}

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApi,
    private val dispatchers: CoroutineDispatchers,
) : CoinRepository {

    override suspend fun getCoinDetails(id: String): Result<CoinDetails> =
        withContext(dispatchers.io) {
            runCatching {
                val response = coinApi.getCoinDetails(id)
                response.data.entries.first().toDomain()
            }.mapError(::mapThrowableToAppError)
        }
}