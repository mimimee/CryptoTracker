package com.example.cryptotracker.core.common

sealed class AppError(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    class Network(cause: Throwable? = null) : AppError("Network error", cause)
    class Parsing(cause: Throwable? = null) : AppError("Parsing error", cause)
    class Unknown(cause: Throwable? = null) : AppError("Unknown error", cause)
}