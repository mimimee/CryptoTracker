package com.example.cryptotracker.feature.markets.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.feature.markets.data.MarketsRepository
import com.example.cryptotracker.feature.markets.data.MarketsRepositoryFake
import com.example.cryptotracker.feature.markets.ui.state.MarketUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MarketsViewModel(
    private val marketsRepository: MarketsRepository = MarketsRepositoryFake(),
) : ViewModel() {
    private val _state = MutableStateFlow<MarketUiState>(MarketUiState.Loading)
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        loadMarkets()
    }

    private fun loadMarkets() {
        _state.value = MarketUiState.Loading

        viewModelScope.launch {
            marketsRepository.loadMarkets()
                .onSuccess { items ->
                    _state.value = MarketUiState.Success(items)
                }
                .onFailure { e ->
                    MarketUiState.Error(e.message ?: "Unknown error")
                }
        }
    }
}