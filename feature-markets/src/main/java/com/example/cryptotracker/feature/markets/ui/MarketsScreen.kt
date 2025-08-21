package com.example.cryptotracker.feature.markets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketsScreen(onOpenCoin: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Markets") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { onOpenCoin("BTC") }) {
                Text(text = "Open Bitcoin")
            }
            Button(onClick = { onOpenCoin("ETH") }) {
                Text(text = "Open Etherium")
            }
            Button(onClick = { onOpenCoin("Doge") }) {
                Text(text = "Open DogeCoin")
            }
        }
    }
}