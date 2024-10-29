package com.tech.mymedicalstoreadminapp.ui_layer.screen.product_screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tech.mymedicalstoreadminapp.R
import com.tech.mymedicalstoreadminapp.data.response.product.ProductModelItem
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.ProductDetailScreenRoute
import com.tech.mymedicalstoreadminapp.ui_layer.component.AsyncImageComponent
import com.tech.mymedicalstoreadminapp.ui.theme.WhiteGreyColor
import com.tech.mymedicalstoreadminapp.viewmodel.ProductViewmodel

@Composable
fun AllProductShowScreen(productViewmodel: ProductViewmodel, navController: NavHostController) {

    LaunchedEffect(Unit) {
       productViewmodel.getAllProducts()
    }
    val getAllProductState = productViewmodel.getAllProducts.collectAsState()
    val allProductList = getAllProductState.value.data ?: emptyList()
    val context = LocalContext.current

    when {
        getAllProductState.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading...")
            }
        }

        getAllProductState.value.data != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WhiteGreyColor),
                contentAlignment = Alignment.BottomEnd
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(allProductList) {
                            ProductItemView(it,
                                onClick = {
                                    navController.navigate(ProductDetailScreenRoute(it.product_id).route)
                                }
                            )
                        }
                    }
                }
            }
        }

        getAllProductState.value.error != null -> {
            Toast.makeText(context, getAllProductState.value.error, Toast.LENGTH_SHORT).show()
        }

    }
}

@Composable
fun ProductItemView(
    productModelItem: ProductModelItem,
    onClick: () -> Unit
) {

    Card(
        onClick = {
            onClick()
        },
        elevation = CardDefaults.cardElevation(4.dp), shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImageComponent(
                imageId = productModelItem.product_image_id,
                imageSize = 100.dp,
                shape = RectangleShape
            )
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp)
            ) {
                Text(
                    text = productModelItem.product_name, style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontStyle = FontStyle.Normal
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = productModelItem.product_category, style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        fontStyle = FontStyle.Normal
                    )
                )

            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.cart_rs, productModelItem.product_price),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontStyle = FontStyle.Normal
                    )
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(R.drawable.star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(8.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = productModelItem.product_rating.toString(), style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }
            }


        }
    }

}