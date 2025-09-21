package com.example.cryptotracker.core.common

inline fun <T> Result<T>.mapError(mapper: (Throwable) -> AppError): Result<T> =
    fold(onSuccess = { Result.success(it) }, onFailure = { Result.failure(mapper(it)) })