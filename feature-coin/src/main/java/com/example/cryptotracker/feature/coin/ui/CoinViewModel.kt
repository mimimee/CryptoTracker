package com.example.cryptotracker.feature.coin.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.feature.coin.data.CoinRepository
import com.example.cryptotracker.feature.coin.ui.state.CoinUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val coinId: String = checkNotNull(savedStateHandle["id"])

    private val _state = MutableStateFlow<CoinUiState>(CoinUiState.Idle)
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh(){
        loadCoinDetails()
    }

    private fun loadCoinDetails() {
        if (_state.value is CoinUiState.Loading) return
        _state.value = CoinUiState.Loading

        viewModelScope.launch {
            coinRepository.getCoinDetails(coinId)
                .onSuccess { coin ->
                    _state.value = CoinUiState.Success(coin)
                }
                .onFailure { e ->
                    _state.value = CoinUiState.Error(e.message ?: "Unknown error")
                }
        }
    }
}