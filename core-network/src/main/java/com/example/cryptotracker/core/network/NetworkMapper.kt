package com.example.cryptotracker.core.network

import com.example.cryptotracker.core.common.AppError
import kotlinx.serialization.SerializationException
import java.io.IOException

fun mapThrowableToAppError(t: Throwable): AppError =
    when (t) {
        is IOException -> AppError.Network(t)
        is SerializationException -> AppError.Parsing(t)
        else -> AppError.Unknown(t)
    }