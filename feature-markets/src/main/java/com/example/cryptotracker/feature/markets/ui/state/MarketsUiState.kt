package com.example.cryptotracker.feature.markets.ui.state

import com.example.cryptotracker.feature.markets.domain.MarketItem

sealed interface MarketsUiState {
    data object Idle : MarketsUiState
    data object Loading : MarketsUiState
    data class Success(val items: List<MarketItem>) : MarketsUiState
    data class Error(val message: String) : MarketsUiState
}