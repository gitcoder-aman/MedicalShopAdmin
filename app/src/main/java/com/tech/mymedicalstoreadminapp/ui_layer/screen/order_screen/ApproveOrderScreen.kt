package com.tech.mymedicalstoreadminapp.ui_layer.screen.order_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.OrderDetailScreenRoute
import com.tech.mymedicalstoreadminapp.viewmodel.OrderViewmodel

@Composable
fun ApproveOrderScreen(orderViewModel: OrderViewmodel, navController: NavHostController) {

    LaunchedEffect(Unit) {
        orderViewModel.getAllOrders()
    }
    val getAllOrderState = orderViewModel.getAllOrdersState.collectAsState()
    val getAllOrderList = getAllOrderState.value.data?.body() ?: emptyList()

    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    when{
        getAllOrderState.value.loading->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Loading...")
            }
        }
        getAllOrderState.value.data!=null->{
            Log.d("@order", "OrderStatusScreen: ${getAllOrderState.value.data}")

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = lazyState
            ) {
                items(getAllOrderList.reversed()) { orderData ->
                    OrderCardView(orderData){
                        navController.navigate(OrderDetailScreenRoute(orderId = orderData.order_id).route)
                    }
                }

            }
        }
        getAllOrderState.value.error!=null->{
            Log.d("@order", "OrderStatusScreen: ${getAllOrderState.value.data}")
            Toast.makeText(context, getAllOrderState.value.error, Toast.LENGTH_SHORT).show()
        }
    }

}