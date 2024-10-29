package com.tech.mymedicalstoreadminapp.ui_layer.screen.stock_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.R
import com.tech.mymedicalstoreadminapp.data.response.user.UserStockResponseItem

@Composable
fun StockUserCardView(userStockResponseItem: UserStockResponseItem) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(300.dp), shape = RoundedCornerShape(16.dp), border = BorderStroke(
            2.dp,
            GreenColor
        ), elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            TextView(text = "Stock Id : ${userStockResponseItem.id}")
            TextView(text = "Product Id : ${userStockResponseItem.product_id}")
            TextView(text = "order Id : ${userStockResponseItem.order_id}")
            TextView(text = "Product Name : ${userStockResponseItem.product_name}")
            TextView(text = "Product Category : ${userStockResponseItem.product_category}")
            TextView(text = "Certified : ${userStockResponseItem.certified}")
            TextView(text = "Product Price : ${userStockResponseItem.product_price}")
            TextView(text = "Product Stock : ${userStockResponseItem.product_stock}")
            TextView(text = "User Name : ${userStockResponseItem.user_name}")
            TextView(text = "User Id : ${userStockResponseItem.user_id}")

            Spacer(modifier = Modifier.height(4.dp))
        }

    }
}

@Composable
fun TextView(
    text: String,
    color: Color = Color.Black,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text, style = TextStyle(
            color = color,
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
    )
}
