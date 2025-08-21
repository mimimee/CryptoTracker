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
import com.example.cryptotracker.core.ui.nav.FeatureEntry
import com.example.cryptotracker.feature.markets.MarketsEntry
import com.example.cryptotracker.ui.theme.CryptoTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { AppRoot() }
    }
}

@Composable
fun AppRoot(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    val entries: List<FeatureEntry> = listOf(
        MarketsEntry()
    )

    CryptoTrackerTheme {
        Scaffold { paddingValues ->
            NavHost(
                navController = navHostController,
                startDestination = "markets",
                modifier = Modifier.padding(paddingValues)
            ) {
                entries.forEach { entry ->
                    entry.register(this, navHostController)
                }
            }
        }
    }
}