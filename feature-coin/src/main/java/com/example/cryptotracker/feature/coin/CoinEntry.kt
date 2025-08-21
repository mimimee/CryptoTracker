package com.example.cryptotracker.feature.coin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cryptotracker.core.ui.nav.FeatureEntry

class CoinEntry : FeatureEntry {
    override val route: String = "coin/{id}"

    override fun register(builder: NavGraphBuilder, navController: NavHostController) {
        builder.composable(
            route = route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            CoinScreen(id = id, onBack = { navController.popBackStack() })
        }
    }
}