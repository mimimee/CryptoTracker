package com.example.cryptotracker.feature.markets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cryptotracker.core.ui.nav.FeatureEntry

class MarketsEntry : FeatureEntry {
    override val route: String = "markets"

    override fun register(builder: NavGraphBuilder, navController: NavHostController) {
        builder.composable(route) {

        }
    }
}

@Composable
fun MarketsScreen(modifier: Modifier = Modifier) {
    Text("Markets Screen")
}