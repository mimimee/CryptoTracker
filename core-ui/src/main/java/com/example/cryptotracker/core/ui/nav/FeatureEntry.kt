package com.example.cryptotracker.core.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureEntry {
    val route: String

    fun register(
        builder: NavGraphBuilder,
        navController: NavHostController,
    )
}