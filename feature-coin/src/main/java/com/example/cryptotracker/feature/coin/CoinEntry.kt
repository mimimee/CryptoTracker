package com.example.cryptotracker.feature.coin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.cryptotracker.core.ui.nav.FeatureEntry

class CoinEntry : FeatureEntry {
    override val route: String
        get() = "coin"

    override fun register(builder: NavGraphBuilder, navController: NavHostController) {
        TODO("Not yet implemented")
    }
}