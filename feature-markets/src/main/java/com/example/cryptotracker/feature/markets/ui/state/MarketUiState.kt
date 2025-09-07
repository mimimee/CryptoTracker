package com.example.cryptotracker.feature.markets.ui.state

import com.example.cryptotracker.feature.markets.domain.MarketItem

sealed interface MarketUiState {
    data object Idle : MarketUiState
    data object Loading : MarketUiState
    data class Success(val items: List<MarketItem>) : MarketUiState
    data class Error(val message: String) : MarketUiState
}