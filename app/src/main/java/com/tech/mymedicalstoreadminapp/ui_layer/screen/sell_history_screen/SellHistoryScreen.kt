package com.tech.mymedicalstoreadminapp.ui_layer.screen.sell_history_screen

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
import com.tech.mymedicalstoreadminapp.viewmodel.SellHistoryViewmodel

@Composable
fun SellHistoryScreen(
    navController: NavHostController,
    sellHistoryViewmodel: SellHistoryViewmodel
) {
    LaunchedEffect(Unit) {
        sellHistoryViewmodel.getAllSellHistory()
    }
    val getAllSellHistoryState = sellHistoryViewmodel.getAllSellHistoryState.collectAsState()
    val getAllSellHistoryList = getAllSellHistoryState.value.data?.body()
    val context = LocalContext.current

    when {
        getAllSellHistoryState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        getAllSellHistoryState.value.data != null -> {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                Spacer(Modifier.height(8.dp))
                TopAppBar(headerName = "Sell History", isCloseIcon = true) {
                    navController.navigateUp()
                }
                Spacer(Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(getAllSellHistoryList!!) {
                        Log.d("@sell", "SellHistoryScreen: ${it.sell_id}")
                        SellHistoryCardView(it)
                    }
                }
            }
        }

        getAllSellHistoryState.value.error != null -> {
            Toast.makeText(context, getAllSellHistoryState.value.error, Toast.LENGTH_SHORT).show()
        }
    }
}