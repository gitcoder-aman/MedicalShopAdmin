package com.tech.mymedicalstoreadminapp.ui_layer.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.AllStockScreenRoute
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.SellHistoryScreenRoute

@Composable
fun DashboardScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sell All History",modifier = Modifier.clickable {
            navController.navigate(SellHistoryScreenRoute.route)
        })
        Spacer(Modifier.height(16.dp))
        Text("Stock All User",modifier = Modifier.clickable {
            navController.navigate(AllStockScreenRoute.route)
        })
    }

}