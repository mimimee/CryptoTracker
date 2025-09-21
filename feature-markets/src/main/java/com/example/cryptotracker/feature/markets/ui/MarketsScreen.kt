package com.example.cryptotracker.feature.markets.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.cryptotracker.feature.markets.domain.MarketItem
import com.example.cryptotracker.feature.markets.ui.state.MarketUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketsScreen(
    modifier: Modifier = Modifier,
    vm: MarketsViewModel = hiltViewModel(),
    onOpenCoin: (String) -> Unit,
) {
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Markets") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { vm.refresh() }) {
                Text(text = "Refresh")
            }
            when (val uiState = state) {
                MarketUiState.Idle -> {}
                MarketUiState.Loading -> Text(text = "Loading")
                is MarketUiState.Error -> Text(text = "Error: ${uiState.message}")
                is MarketUiState.Success -> MarketList(
                    items = uiState.items,
                    onClick = onOpenCoin
                )

            }
        }
    }
}

@Composable
private fun MarketList(
    modifier: Modifier = Modifier,
    items: List<MarketItem>,
    onClick: (String) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(items = items, key = { it.id }) { marketItem ->
            Text(
                text = "${marketItem.name} ${marketItem.priceUsd} ${marketItem.change24h}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(marketItem.id) }
                    .padding(vertical = 8.dp)
            )
        }
    }
}