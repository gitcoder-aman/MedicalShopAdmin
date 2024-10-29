package com.tech.mymedicalstoreadminapp.ui_layer.screen.stock_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.ui_layer.component.TopAppBar
import com.tech.mymedicalstoreadminapp.viewmodel.UserStockViewmodel

@Composable
fun AllStockScreen(navController: NavHostController, userStockViewmodel: UserStockViewmodel) {

    val getAllUserStockState = userStockViewmodel.getAllUserStockState.collectAsState()
    val getAllUserStockList = getAllUserStockState.value.data?.body()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        userStockViewmodel.getAllUserStock()
    }
    when{
        getAllUserStockState.value.loading-> {
            Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                CircularProgressIndicator(color = GreenColor)
            }
        }
        getAllUserStockState.value.data != null -> {
            Log.d("@stock", "AllStockScreen: ${getAllUserStockState.value.data!!.body()?.size}")
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                Spacer(Modifier.height(8.dp))
                TopAppBar(headerName = "Stock User", isCloseIcon = true) {
                    navController.navigateUp()
                }
                Spacer(Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(getAllUserStockList!!) {
                        Log.d("@stock", "Stock Screen id : ${it.id}")
                        StockUserCardView(it)
                    }
                }
            }
        }
        getAllUserStockState.value.error != null -> {
            Toast.makeText(context, getAllUserStockState.value.error, Toast.LENGTH_SHORT).show()
            Log.d("@stock", "AllStockScreen: ${getAllUserStockState.value.error}")
        }
    }
}