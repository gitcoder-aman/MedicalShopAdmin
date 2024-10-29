package com.tech.mymedicalstoreadminapp.ui_layer.screen.order_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.binayshaw7777.kotstep.model.tabVerticalWithLabel
import com.binayshaw7777.kotstep.ui.vertical.VerticalStepper
import com.tech.mymedicalstoreadminapp.R
import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.ui_layer.component.AsyncImageComponent
import com.tech.mymedicalstoreadminapp.ui_layer.component.TopAppBar
import com.tech.mymedicalstoreadminapp.ui_layer.screen.all_user_screen.ButtonView
import com.tech.mymedicalstoreadminapp.viewmodel.OrderViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.ProductViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.SellHistoryViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.UserStockViewmodel
import java.util.Locale

@Composable
fun OrderDetailScreen(
    orderId: String,
    navController: NavHostController,
    orderViewmodel: OrderViewmodel,
    productViewmodel: ProductViewmodel,
    userStockViewmodel: UserStockViewmodel,
    sellHistoryViewmodel: SellHistoryViewmodel
) {

    LaunchedEffect(Unit) {
        orderViewmodel.getSpecificOrder(orderId)
    }
    val getSpecificOrderState = orderViewmodel.getSpecificOrderState.collectAsState()
    val getShippedOrderState = orderViewmodel.updateShippedOrderState.collectAsState()
    val getOutOfDeliveryOrderState = orderViewmodel.updateOutOfDeliveryOrderState.collectAsState()
    val getDeliveredOrderState = orderViewmodel.updateDeliveredOrderState.collectAsState()
    val getCancelledOrderState = orderViewmodel.updateCancelledOrderState.collectAsState()
    val doApprovedOrderState = orderViewmodel.doApprovedOrder.collectAsState()
    val userStockState = userStockViewmodel.addUserStockState.collectAsState()
    val addSellHistoryState = sellHistoryViewmodel.addInSellHistoryState.collectAsState()


    var showDialog by remember { mutableStateOf(false) }


    val context = LocalContext.current
    val scrollSate = rememberScrollState()

    when {
        doApprovedOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        doApprovedOrderState.value.data != null -> {

            LaunchedEffect(Unit) {
                orderViewmodel.getSpecificOrder(orderId)
            }
            orderViewmodel.resetUpdateOrderState()
            Toast.makeText(
                context,
                doApprovedOrderState.value.data!!.body()?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        doApprovedOrderState.value.error != null -> {
            Toast.makeText(context, "${doApprovedOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    when {
        getShippedOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getShippedOrderState.value.data != null -> {

            LaunchedEffect(Unit) {
                orderViewmodel.getSpecificOrder(orderId)
            }
            orderViewmodel.resetUpdateOrderState()
            Log.d("@order_details", "OrderDetailScreen: ${getShippedOrderState.value.data}")
            Toast.makeText(
                context,
                getShippedOrderState.value.data!!.body()?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        getShippedOrderState.value.error != null -> {
            Log.d("@order_details", "OrderDetailScreen: ${getShippedOrderState.value.error}")
            Toast.makeText(context, "${getShippedOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    when {
        getOutOfDeliveryOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getOutOfDeliveryOrderState.value.data != null -> {

            LaunchedEffect(Unit) {
                orderViewmodel.getSpecificOrder(orderId)
            }
            orderViewmodel.resetUpdateOrderState()
            Log.d("@order_details", "OrderDetailScreen: ${getOutOfDeliveryOrderState.value.data}")
            Toast.makeText(
                context,
                getOutOfDeliveryOrderState.value.data!!.body()?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        getOutOfDeliveryOrderState.value.error != null -> {
            Log.d("@order_details", "OrderDetailScreen: ${getOutOfDeliveryOrderState.value.error}")
            Toast.makeText(context, "${getOutOfDeliveryOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    when {
        getDeliveredOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getDeliveredOrderState.value.data != null -> {

            LaunchedEffect(Unit) {
                orderViewmodel.getSpecificOrder(orderId)
            }
            orderViewmodel.resetUpdateOrderState()
            Log.d("@order_details", "OrderDetailScreen: ${getDeliveredOrderState.value.data}")
            Toast.makeText(
                context,
                getDeliveredOrderState.value.data!!.body()?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        getDeliveredOrderState.value.error != null -> {
            Log.d("@order_details", "OrderDetailScreen: ${getDeliveredOrderState.value.error}")
            Toast.makeText(context, "${getDeliveredOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    when {
        getCancelledOrderState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        getCancelledOrderState.value.data != null -> {

            LaunchedEffect(Unit) {
                orderViewmodel.getSpecificOrder(orderId)
            }
            orderViewmodel.resetUpdateOrderState()
            Log.d("@order_details", "OrderDetailScreen: ${getCancelledOrderState.value.data}")
            Toast.makeText(
                context,
                getCancelledOrderState.value.data!!.body()?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        getCancelledOrderState.value.error != null -> {
            Log.d("@order_details", "OrderDetailScreen: ${getCancelledOrderState.value.error}")
            Toast.makeText(context, "${getCancelledOrderState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    when {
        getSpecificOrderState.value.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        getSpecificOrderState.value.error != null -> {
            Log.d("@order_details", "OrderDetailScreen: ${getSpecificOrderState.value.error}")
            Toast.makeText(context, getSpecificOrderState.value.error, Toast.LENGTH_SHORT).show()
        }

        getSpecificOrderState.value.data != null -> {
            val orderData = getSpecificOrderState.value.data!!.body()!![0]
            LaunchedEffect(Unit) {
                productViewmodel.getProductById(orderData.product_id)
            }
            val getSpecificProductState = productViewmodel.getSpecificProduct.collectAsState()

            val updateProductResponseData =
                productViewmodel.updateProductResponseData.collectAsState()
            var productStock = 0

            when {
                getSpecificProductState.value.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Loading...")
                    }
                }

                getSpecificProductState.value.data != null -> {

                    productStock = getSpecificProductState.value.data!![0].product_stock
                    productViewmodel.resetProductUpdateScreenState()
                    Log.d("@stock", "OrderDetailScreen data: ${getSpecificProductState.value.data}")
                }

                getSpecificProductState.value.error != null -> {
                    Log.d(
                        "@stock",
                        "OrderDetailScreen error: ${getSpecificProductState.value.data}"
                    )
                    Toast.makeText(
                        context,
                        "${getSpecificProductState.value.error}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }


            when {
                updateProductResponseData.value.loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Loading...")
                    }
                }

                updateProductResponseData.value.data != null -> {
                    Log.d(
                        "@stock",
                        "OrderDetailScreen data: ${updateProductResponseData.value.data}"
                    )

                    Toast.makeText(
                        context,
                        updateProductResponseData.value.data!!.body()?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                updateProductResponseData.value.error != null -> {
                    Log.d(
                        "@stock",
                        "OrderDetailScreen error: ${updateProductResponseData.value.data}"
                    )

                    Toast.makeText(
                        context,
                        "${updateProductResponseData.value.error}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .background(Color.White)
                ) {
                    Spacer(Modifier.height(8.dp))
                    TopAppBar(headerName = "Order Details", isCloseIcon = true) {
                        navController.navigateUp()
                    }
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                    Spacer(Modifier.height(8.dp))

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollSate)
                            .padding(8.dp)
                            .background(Color.White)
                    ) {

                        ProductDetailCard(orderData)

                        Spacer(Modifier.height(8.dp))
                        ShippingDetailCard(orderData)

                        Spacer(Modifier.height(8.dp))
                        PriceDetailCard(orderData)
                        Spacer(Modifier.height(8.dp))
                        OrderUpdateCardButton(orderData,
                            approveOnClick = {
                                orderViewmodel.updateOrderApproved(
                                    orderId = orderData.order_id,
                                    isApprovedOrder = 1,
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )
                                //manage stock of product
                                //stock get of order product and then minus in stock of product with order Qty
                                productViewmodel.updateStockProductById(
                                    productId = orderData.product_id,
                                    productStock = productStock - orderData.product_quantity
                                )
                            },
                            disApproveOnClick = {
                                orderViewmodel.updateOrderApproved(
                                    orderId = orderData.order_id,
                                    isApprovedOrder = 0,
                                    orderStatusStep = (orderData.order_status.toInt() - 1).toString()
                                )
                            },
                            shippedOnClick = {
                                orderViewmodel.updateShippedDateOrder(
                                    orderId = orderData.order_id,
                                    shippedDate = java.util.Date().toString(),
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )
                            },
                            outOfDeliveryOnClick = {
                                orderViewmodel.updateOutOfDeliveryDateOrder(
                                    orderId = orderData.order_id,
                                    outOfDeliveryDate = java.util.Date().toString(),
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )
                            },
                            deliveredOnClick = {
                                orderViewmodel.updateDeliveredDateOrder(
                                    orderId = orderData.order_id,
                                    deliveredDate = java.util.Date().toString(),
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )

                                //manage the user stock
                                userStockViewmodel.addOrderInUserStock(
                                    userId = orderData.user_id,
                                    orderId = orderData.order_id,
                                    productId = orderData.product_id,
                                    productName = orderData.product_name,
                                    userName = orderData.user_name,
                                    certified = true,
                                    productStock = orderData.product_quantity,
                                    productPrice = orderData.product_price,
                                    productCategory = orderData.product_category
                                )
                                if (userStockState.value.data != null) {
                                    userStockViewmodel.resetAddStockUserState()
                                    Toast.makeText(
                                        context,
                                        userStockState.value.data!!.body()?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(context, userStockState.value.error.toString(), Toast.LENGTH_SHORT).show()
                                }
                                //add in sell history which product was sell and how much
                                sellHistoryViewmodel.addInSellHistory(
                                    userId = orderData.user_id,
                                    productId = orderData.product_id,
                                    quantity = orderData.product_quantity.toString(),
                                    remainingStock = (productStock - orderData.product_quantity).toString(),
                                    dateOfSell = java.util.Date().toString(),
                                    totalAmount = orderData.totalPrice.toString(),
                                    productPrice = orderData.product_price.toString(),
                                    productName = orderData.product_name,
                                    productCategory = orderData.product_category,
                                    userName = orderData.user_name
                                )
                                if (addSellHistoryState.value.data != null) {
                                    sellHistoryViewmodel.resetAddInSellHistoryState()
                                    Toast.makeText(
                                        context,
                                        addSellHistoryState.value.data!!.body()?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        addSellHistoryState.value.error.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            },
                            cancelOnClick = {
                                orderViewmodel.updateCancelledStatusOrder(
                                    orderId = orderData.order_id,
                                    cancelStatus = "Cancelled",
                                    orderStatusStep = (orderData.order_status.toInt() + 1).toString()
                                )
                            }
                        )
                    }
                }
            }
            Log.d(
                "@order_details",
                "OrderDetailScreen: ${getSpecificOrderState.value.data!!.body()}"
            )
        }
    }

}

@Composable
fun OrderUpdateCardButton(
    orderData: MedicalOrderResponseItem,
    approveOnClick: () -> Unit = {},
    disApproveOnClick: () -> Unit = {},
    shippedOnClick: () -> Unit = {},
    outOfDeliveryOnClick: () -> Unit = {},
    deliveredOnClick: () -> Unit = {},
    cancelOnClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Approved",
                    color = GreenColor,
                    enabled = orderData.isApproved == 0
                ) {
                    approveOnClick()
                }
                ButtonView(
                    text = "DisApproved",
                    color = Color.Red,
                    enabled = orderData.isApproved == 1 && orderData.shipped_date == "null"
                ) {
                    disApproveOnClick()
                }
            }
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Shipped",
                    color = GreenColor,
                    enabled = orderData.shipped_date == "null" && orderData.out_of_delivery_date == "null" && orderData.isApproved == 1
                ) {
                    shippedOnClick()
                }
                ButtonView(
                    text = "Out of Delivery",
                    color = GreenColor,
                    enabled = orderData.shipped_date != "null" && orderData.delivered_date == "null" && orderData.out_of_delivery_date == "null"
                ) {
                    outOfDeliveryOnClick()
                }
            }
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Delivered",
                    color = GreenColor,
                    enabled = orderData.out_of_delivery_date != "null" && orderData.delivered_date == "null"
                ) {
                    deliveredOnClick()
                }
                ButtonView(
                    text = "Cancel Order",
                    color = Color.Red,
                    enabled = orderData.delivered_date == "null"
                ) {
                    cancelOnClick()
                }
            }
        }
    }

}

@Composable
fun PriceTextView(
    text: String,
    price: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text, style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_light)),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        )
        Text(
            text = stringResource(R.string.rs) + " " + price, style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_light)),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        )
    }
}

@Composable
fun PriceDetailCard(orderData: MedicalOrderResponseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Price Details", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Selling Price",
                price = orderData.product_price.toString()
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Subtotal Price",
                price = orderData.subtotal_price.toString()
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Extra Discount",
                price = orderData.discount_price
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Delivery Charge",
                price = orderData.delivery_charge.toString()
            )
            Spacer(Modifier.height(8.dp))
            PriceTextView(
                text = "Tax Charge",
                price = orderData.tax_charge.toString()
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))

            PriceTextView(
                text = "Total Price",
                price = orderData.totalPrice.toString()
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Cash on Delivery : " + stringResource(R.string.rs) + " " + "${orderData.totalPrice}",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )

        }
    }
}

@Composable
fun ProductDetailCard(orderData: MedicalOrderResponseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Order ID - ${orderData.order_id}", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = orderData.product_name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }, style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Category - ${
                            orderData.product_category.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            }
                        }",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Qty - ${orderData.product_quantity}", style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.rs) + " ${orderData.subtotal_price}",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )

                }
                AsyncImageComponent(
                    imageId = orderData.product_image_id,
                    imageSize = 100.dp,
                    padding = 8.dp,
                    shape = RectangleShape
                )
            }
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(16.dp))
            VerticalStepper(
                style = tabVerticalWithLabel(
                    totalSteps = 5,
                    currentStep = orderData.order_status.toInt(),
                    trailingLabels = listOf(
                        { Text("Pending  ${orderData.order_date.substring(0, 10)}") },
                        {
                            Text(
                                "Ordered Approved ${
                                    orderData.order_date.substring(
                                        0,
                                        10
                                    )
                                }"
                            )
                        },
                        {
                            Text(
                                "Shipped  ${
                                    if (orderData.shipped_date != "null")
                                        orderData.shipped_date.substring(0, 10) else ""
                                }"
                            )
                        },
                        {
                            Text(
                                "Out of Delivery  ${
                                    if (orderData.out_of_delivery_date != "null")
                                        orderData.out_of_delivery_date.substring(0, 10) else ""
                                }"
                            )
                        },
                        {
                            Text(
                                "Delivered  ${
                                    if (orderData.delivered_date != "null")
                                        orderData.delivered_date.substring(0, 10) else ""
                                }"
                            )
                        },
                    )
                ), modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ShippingDetailCard(orderData: MedicalOrderResponseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Shipping Details", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(Modifier.height(8.dp))

            Text(
                text = orderData.user_name, style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Address : ${orderData.user_address}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Street : ${orderData.user_street}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Mobile No : ${orderData.user_mobile}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "City : ${orderData.user_city}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "State : ${orderData.user_state}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Pincode : ${orderData.user_pinCode}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_light)),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
            Spacer(Modifier.height(4.dp))
        }
    }
}
