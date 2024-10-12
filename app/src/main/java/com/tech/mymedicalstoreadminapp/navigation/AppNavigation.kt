package com.tech.mymedicalstoreadminapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tech.mymedicalstoreadminapp.MainActivity
import com.tech.mymedicalstoreadminapp.screen.ProductAddScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ProductAddScreenRoute,
    ) {

//        composable<ProductAddScreenRoute> {
//            ProductAddScreen()
//        }
        composable<BottomScreenRoute> {
            MainActivity()
        }
    }

}