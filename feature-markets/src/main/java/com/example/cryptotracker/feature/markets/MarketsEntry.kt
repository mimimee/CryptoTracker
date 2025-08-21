package com.example.cryptotracker.feature.markets

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cryptotracker.core.ui.nav.FeatureEntry

class MarketsEntry : FeatureEntry {
    override val route: String = "markets"

    override fun register(builder: NavGraphBuilder, navController: NavHostController) {
        builder.composable(route) {
            MarketsScreen(
                onOpenCoin = { id ->
                    navController.navigate("coin/$id")
                }
            )
        }
    }
}