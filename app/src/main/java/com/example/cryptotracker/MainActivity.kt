package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cryptotracker.core.navigation.FeatureEntry
import com.example.cryptotracker.core.navigation.routes.MarketsRoute
import com.example.cryptotracker.feature.coin.navigation.CoinEntry
import com.example.cryptotracker.feature.markets.navigation.MarketsEntry
import com.example.cryptotracker.ui.theme.CryptoTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { AppRoot() }
    }
}

@Composable
fun AppRoot() {
    val navHostController = rememberNavController()
    val entries: List<FeatureEntry> = listOf(
        MarketsEntry(),
        CoinEntry()
    )

    CryptoTrackerTheme {
        Scaffold { paddingValues ->
            NavHost(
                navController = navHostController,
                startDestination = MarketsRoute,
                modifier = Modifier.padding(paddingValues)
            ) {
                entries.forEach { entry ->
                    entry.register(this, navHostController)
                }
            }
        }
    }
}