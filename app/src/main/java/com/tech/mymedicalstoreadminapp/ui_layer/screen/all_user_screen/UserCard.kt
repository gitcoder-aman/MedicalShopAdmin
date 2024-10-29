package com.tech.mymedicalstoreadminapp.ui_layer.screen.all_user_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import com.tech.mymedicalstoreadminapp.ui_layer.component.AsyncImageComponent

@Composable
fun UserCardView(
    userResponseItem: GetAllUsersResponseItem,
    approveOnClick: () -> Unit,
    disApproveOnClick: () -> Unit,
    blockOnClick: () -> Unit,
    unblockOnClick: () -> Unit,
    deleteOnClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(500.dp), shape = RoundedCornerShape(16.dp), border = BorderStroke(
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
            AsyncImageComponent(
                imageId = userResponseItem.user_image_id,
                imageSize = 90.dp,
                shape = CircleShape,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextView(text = "User Id: ${userResponseItem.user_id}")
            TextView(text = "User Name: ${userResponseItem.name}")
            TextView(text = "Email: ${userResponseItem.email}")
            TextView(text = "Password: ${userResponseItem.password}")
            TextView(text = "Phone Number: ${userResponseItem.phone_number}")
            TextView(text = "PinCode: ${userResponseItem.pinCode}")
            TextView(text = "Address: ${userResponseItem.address}")
            TextView(text = "is Approved: ${userResponseItem.isApproved}")
            TextView(text = "is Blocked: ${userResponseItem.block}")
            TextView(text = "Date of creation: ${userResponseItem.date_of_account_creation}")
            TextView(text = "UserImageId: ${userResponseItem.user_image_id}")

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Approved",
                    color = GreenColor,
                    enabled = userResponseItem.isApproved == 0
                ) {
                    approveOnClick()
                }
                ButtonView(
                    text = "DisApproved",
                    color = Color.Red,
                    enabled = userResponseItem.isApproved == 1
                ) {
                    disApproveOnClick()
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Block",
                    color = GreenColor,
                    enabled = userResponseItem.block == 0
                ) {
                    blockOnClick()
                }
                ButtonView(
                    text = "UnBlock",
                    color = Color.Red,
                    enabled = userResponseItem.block == 1
                ) {
                    unblockOnClick()
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonView(
                    text = "Delete",
                    color = Color.Red,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    deleteOnClick()
                }
            }


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

@Composable
fun ButtonView(
    text: String,
    color: Color,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        ), elevation = ButtonDefaults.buttonElevation(4.dp),
        enabled = enabled,
        modifier = modifier

    ) {
        Text(text = text)
    }

}