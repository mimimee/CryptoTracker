package com.example.cryptotracker.feature.markets.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.feature.markets.data.MarketsRepository
import com.example.cryptotracker.feature.markets.ui.state.MarketUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MarketsViewModel(
    private val marketsRepository: MarketsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<MarketUiState>(MarketUiState.Idle)
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        loadMarkets()
    }

    private fun loadMarkets() {
        if (_state.value is MarketUiState.Loading) return
        _state.value = MarketUiState.Loading

        viewModelScope.launch {
            marketsRepository.loadMarkets()
                .onSuccess { items ->
                    _state.value = MarketUiState.Success(items)
                }
                .onFailure { e ->
                    _state.value = MarketUiState.Error(e.message ?: "Unknown error")
                }
        }
    }
}