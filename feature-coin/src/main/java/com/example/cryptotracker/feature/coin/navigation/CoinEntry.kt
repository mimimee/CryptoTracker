package com.example.cryptotracker.feature.coin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.cryptotracker.core.navigation.FeatureEntry
import com.example.cryptotracker.core.navigation.routes.CoinRoute
import com.example.cryptotracker.feature.coin.ui.CoinScreen

class CoinEntry : FeatureEntry {
    override fun register(builder: NavGraphBuilder, navController: NavHostController) {
        builder.composable<CoinRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CoinRoute>()
            CoinScreen(id = route.id, onBack = { navController.popBackStack() })
        }
    }
}