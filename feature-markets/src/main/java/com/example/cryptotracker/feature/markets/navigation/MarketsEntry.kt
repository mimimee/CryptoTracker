package com.example.cryptotracker.feature.markets.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cryptotracker.core.navigation.FeatureEntry
import com.example.cryptotracker.core.navigation.routes.CoinRoute
import com.example.cryptotracker.core.navigation.routes.MarketsRoute
import com.example.cryptotracker.feature.markets.ui.MarketsScreen

class MarketsEntry : FeatureEntry {
    override fun register(builder: NavGraphBuilder, navController: NavHostController) {
        builder.composable<MarketsRoute> {
            MarketsScreen(
                onOpenCoin = { coinId ->
                    navController.navigate(CoinRoute(id = coinId))
                }
            )
        }
    }
}