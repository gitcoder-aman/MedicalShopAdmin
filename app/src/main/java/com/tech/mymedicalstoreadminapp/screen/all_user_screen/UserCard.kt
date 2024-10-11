package com.tech.mymedicalstoreadminapp.screen.all_user_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.R
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem

@Composable
fun UserCardView(
    userResponseItem: GetAllUsersResponseItem,
    approveOnClick: () -> Unit,
    disApproveOnClick: () -> Unit
) {

    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(250.dp), shape = RoundedCornerShape(16.dp), border = BorderStroke(
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
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    TextView(text = "User Id: ")
                    TextView(text = "User Name: ")
                    TextView(text = "Email: ")
                    TextView(text = "Phone Number: ")
                    TextView(text = "PinCode: ")
                    TextView(text = "Address: ")
                    TextView(text = "is Approved: ")
                    TextView(text = "is Blocked: ")
                    TextView(text = "Date of creation: ")
                }
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    TextView(text = userResponseItem.user_id, fontSize = 14)
                    TextView(text = userResponseItem.name)
                    TextView(text = userResponseItem.email)
                    TextView(text = userResponseItem.phone_number)
                    TextView(text = userResponseItem.pinCode)
                    TextView(text = userResponseItem.address)
                    TextView(text = userResponseItem.isApproved.toString())
                    TextView(text = userResponseItem.block.toString())
                    TextView(text = userResponseItem.date_of_account_creation)
                }
            }
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
    enabled : Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        ), elevation = ButtonDefaults.buttonElevation(4.dp),
        enabled = enabled

    ) {
        Text(text = text)
    }

}