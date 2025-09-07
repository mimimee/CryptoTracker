package com.example.cryptotracker.core.network

import com.example.cryptotracker.core.common.AppError
import retrofit2.Retrofit

suspend fun getTextViaRetrofit(retrofit: Retrofit, url: String): Result<String> {
    return runCatching {
        val service = retrofit.create(RawService::class.java)
        val body = service.getRaw(url)
        body.string()
    }.mapError(::mapThrowableToAppError)
}

private inline fun <T> Result<T>.mapError(mapper: (Throwable) -> AppError): Result<T> = fold(
    onSuccess = { Result.success(it) },
    onFailure = { Result.failure(mapper(it)) }
)