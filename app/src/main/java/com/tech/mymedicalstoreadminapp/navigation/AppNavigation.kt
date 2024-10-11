package com.tech.mymedicalstoreadminapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tech.mymedicalstoreadminapp.screen.ProductAddScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ProductAddScreenRoute,
    ) {

        composable<ProductAddScreenRoute> {
            ProductAddScreen()
        }
    }

}