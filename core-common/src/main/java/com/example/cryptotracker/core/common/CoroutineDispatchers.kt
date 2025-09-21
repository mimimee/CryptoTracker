package com.example.cryptotracker.core.common

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
}

data class AppCoroutineDispatchers(
    override val io: CoroutineDispatcher,
    override val default: CoroutineDispatcher,
    override val main: CoroutineDispatcher,
) : CoroutineDispatchers