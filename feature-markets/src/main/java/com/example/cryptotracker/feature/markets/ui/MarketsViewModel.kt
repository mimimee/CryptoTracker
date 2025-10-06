package com.example.cryptotracker.feature.markets.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.feature.markets.data.MarketsRepository
import com.example.cryptotracker.feature.markets.ui.state.MarketsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketsViewModel @Inject constructor(
    private val marketsRepository: MarketsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<MarketsUiState>(MarketsUiState.Idle)
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        loadMarkets()
    }

    private fun loadMarkets() {
        if (_state.value is MarketsUiState.Loading) return
        _state.value = MarketsUiState.Loading

        viewModelScope.launch {
            marketsRepository.loadMarkets()
                .onSuccess { items ->
                    _state.value = MarketsUiState.Success(items)
                }
                .onFailure { e ->
                    _state.value = MarketsUiState.Error(e.message ?: "Unknown error")
                }
        }
    }
}