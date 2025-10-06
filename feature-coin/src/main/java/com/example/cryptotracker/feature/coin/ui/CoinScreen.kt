package com.example.cryptotracker.feature.coin.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.cryptotracker.feature.coin.ui.state.CoinUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinScreen(
    modifier: Modifier = Modifier,
    vm: CoinViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Coin") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (val uiState = state) {
                CoinUiState.Idle -> {}
                CoinUiState.Loading -> CircularProgressIndicator()
                is CoinUiState.Error -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = uiState.message)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = { vm.refresh() }) {
                        Text("Retry")
                    }
                }

                is CoinUiState.Success -> {
                    val coin = uiState.coin
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("${coin.name} (${coin.symbol})")
                        Text("$${"%.2f".format(coin.priceUsd)}")
                        Text("${"%.2f".format(coin.change24h)}%")
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = { vm.refresh() }) {
                            Text("Refresh")
                        }
                    }
                }
            }
        }
    }
}