package com.tech.mymedicalstoreadminapp.ui_layer.screen.product_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tech.mymedicalstoreadminapp.R
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.ui.theme.LightGreenColor
import com.tech.mymedicalstoreadminapp.ui_layer.component.AlertDialogComponent
import com.tech.mymedicalstoreadminapp.ui_layer.component.AsyncImageComponent
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.UpdateProductScreenRoute
import com.tech.mymedicalstoreadminapp.viewmodel.ProductViewmodel

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productViewmodel: ProductViewmodel,
    productId: String
) {

    LaunchedEffect(Unit) {
        productViewmodel.getProductById(productId)
    }
    val getSpecificProductState = productViewmodel.getSpecificProduct.collectAsState()
    val updateProductScreenState = productViewmodel.updateProductScreenData.collectAsState()
    val deleteProductState = productViewmodel.deleteProductState.collectAsState()

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var productId by remember { mutableStateOf("") }

    Log.d("@product_detail", "ProductDetailScreen: ${getSpecificProductState.value.data?.get(0)}")

    when {
        getSpecificProductState.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        getSpecificProductState.value.data != null -> {
            val productItem = getSpecificProductState.value.data!![0]
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    item {
                        ProductThumbnail(
                            productImageId = productItem.product_image_id,
                            onEditClick = {
                                updateProductScreenState.value.productId.value =
                                    productItem.product_id
                                updateProductScreenState.value.productName.value =
                                    productItem.product_name
                                updateProductScreenState.value.productCategory.value =
                                    productItem.product_category
                                updateProductScreenState.value.productPrice.value =
                                    productItem.product_price.toString()
                                updateProductScreenState.value.productDescription.value =
                                    productItem.product_description
                                updateProductScreenState.value.productPower.value =
                                    productItem.product_power
                                updateProductScreenState.value.productRating.value =
                                    productItem.product_rating.toString()
                                updateProductScreenState.value.productStock.value =
                                    productItem.product_stock.toString()
                                updateProductScreenState.value.productExpiryDate.value =
                                    productItem.product_expiry_date
                                updateProductScreenState.value.productImage.value = null

                                navController.navigate(UpdateProductScreenRoute.route)
                            },
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Id-> ${productItem.product_id}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Name-> ${productItem.product_name}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Category-> ${productItem.product_category}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Description-> ${productItem.product_description}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Price-> ${productItem.product_price}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Stock-> ${productItem.product_stock}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Expiry Date-> ${productItem.product_expiry_date}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Rating-> ${productItem.product_rating}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Power-> ${productItem.product_power}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(LightGreenColor)
                            ) {
                                Text(
                                    text = "Product Image Id-> ${productItem.product_image_id}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(16.dp))

                            Image(
                                imageVector = Icons.Default.Delete,
                                colorFilter = ColorFilter.tint(Color.White),
                                contentDescription = "delete",
                                modifier = Modifier
                                    .background(Color.Red)
                                    .fillMaxWidth()
                                    .shadow(
                                        elevation = 4.dp,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .size(35.dp)
                                    .clickable {
                                        showDialog = true
                                        productId = productItem.product_id
                                    }
                            )
                        }
                    }
                }
            }
        }

        getSpecificProductState.value.error != null -> {
            Toast.makeText(context, getSpecificProductState.value.error, Toast.LENGTH_SHORT).show()
        }
    }

    when {
        deleteProductState.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        deleteProductState.value.data != null -> {
            if (deleteProductState.value.data!!.body()?.status == 200) {
                productViewmodel.resetDeleteProductState()
                navController.navigateUp()
            }
            Log.d(
                "@delete",
                "ProductDetailScreen: ${deleteProductState.value.data!!.body()?.message}"
            )
            Toast.makeText(
                context,
                deleteProductState.value.data?.body()?.message,
                Toast.LENGTH_SHORT
            )
                .show()
        }

        deleteProductState.value.error != null -> {
            Log.d("@delete", "ProductDetailScreen: ${deleteProductState.value.error}")
            Toast.makeText(context, deleteProductState.value.error, Toast.LENGTH_SHORT).show()
        }
    }

    if (showDialog) {
        AlertDialogComponent(
            title = "Delete Product",
            text = "Are you sure you want to delete this product?",
            onConfirm = {
                productViewmodel.deleteProduct(productId)
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

}

@Composable
fun ProductThumbnail(
    productImageId: String,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImageComponent(
            imageId = productImageId,
            imageSize = 100.dp,
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
                .background(Color.White, shape = CircleShape)
                .padding(4.dp)
                .size(35.dp)
                .align(Alignment.TopStart)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onBackClick()
                }
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "",
            modifier = Modifier
                .padding(end = 12.dp, top = 12.dp)
                .background(Color.White, shape = CircleShape)
                .padding(4.dp)
                .align(Alignment.TopEnd)
                .size(35.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onEditClick()
                },
            tint = Color.Black
        )
    }

}