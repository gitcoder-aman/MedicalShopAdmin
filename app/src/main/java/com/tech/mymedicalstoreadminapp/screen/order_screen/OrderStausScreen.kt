package com.tech.mymedicalstoreadminapp.screen.order_screen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.tech.mymedicalstoreadminapp.screen.all_user_screen.UserCardView
import com.tech.mymedicalstoreadminapp.viewmodel.MedicalViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.OrderViewmodel

@Composable
fun OrderStatusScreen(orderViewModel: OrderViewmodel = hiltViewModel()) {

    val getAllOrderState = orderViewModel.getAllOrders.collectAsState()
    val getAllOrderList = getAllOrderState.value.data?.body() ?: emptyList()
    val doApprovedOrderState = orderViewModel.doApprovedOrder.collectAsState()

    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    when{
        doApprovedOrderState.value.loading->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Loading...")
            }
        }
        doApprovedOrderState.value.data!=null->{

            LaunchedEffect(key1 = getAllOrderState) {
                orderViewModel.getAllOrders()
                Toast.makeText(
                    context,
                    "${doApprovedOrderState.value.data}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Toast.makeText(context, "${doApprovedOrderState.value.data}", Toast.LENGTH_SHORT).show()
        }
        doApprovedOrderState.value.error!=null->{
            Toast.makeText(context, "${doApprovedOrderState.value.error}", Toast.LENGTH_SHORT).show()
        }

    }
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
                items(getAllOrderList) { orderData ->
                    OrderCardView(orderData, approveOnClick = {
                        orderViewModel.updateOrderApproved(
                            orderId = orderData.order_id,
                            isApprovedOrder = 1
                        )
                        Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show()
                    }, disApproveOnClick = {
                        orderViewModel.updateOrderApproved(
                            orderId = orderData.order_id,
                            isApprovedOrder = 0
                        )
                        Toast.makeText(context, "DisApproved", Toast.LENGTH_SHORT).show()
                    })
                }

            }
        }
        getAllOrderState.value.error!=null->{
            Log.d("@order", "OrderStatusScreen: ${getAllOrderState.value.data}")
            Toast.makeText(context, getAllOrderState.value.error, Toast.LENGTH_SHORT).show()
        }
    }

}