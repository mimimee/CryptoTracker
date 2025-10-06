package com.example.cryptotracker.feature.coin.ui.state

import com.example.cryptotracker.feature.coin.domain.CoinDetails

sealed interface CoinUiState {
    data object Idle : CoinUiState
    data object Loading : CoinUiState
    data class Success(val coin: CoinDetails) : CoinUiState
    data class Error(val message: String) : CoinUiState
}