package com.example.cryptotracker.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureEntry {
    fun register(
        builder: NavGraphBuilder,
        navController: NavHostController,
    )
}