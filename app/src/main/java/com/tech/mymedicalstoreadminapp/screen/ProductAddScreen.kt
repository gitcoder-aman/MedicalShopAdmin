package com.tech.mymedicalstoreadminapp.screen

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.tech.mymedicalstoreadminapp.utils.isFloat
import com.tech.mymedicalstoreadminapp.utils.isInteger
import com.tech.mymedicalstoreadminapp.R
import com.tech.mymedicalstoreadminapp.screen_state.ProductAddScreenState
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.ui.theme.darkWhiteColor
import com.tech.mymedicalstoreadminapp.viewmodel.MedicalViewmodel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ProductAddScreen(
    bottomNavController: NavHostController,
    medicalViewmodel: MedicalViewmodel = hiltViewModel()
) {

    val context = LocalContext.current
    val productAddResponseState = medicalViewmodel.addProductResponseState.collectAsState()
    val productAddScreenState = medicalViewmodel.addProductScreenData.collectAsState()

    when {
        productAddResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        productAddResponseState.value.data != null -> {
            if (productAddResponseState.value.data!!.isSuccessful) {
                LaunchedEffect(Unit) {
                    medicalViewmodel.resetProductAddScreenState()
                    Toast.makeText(
                        context, productAddResponseState.value.data!!.message(), Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }

        productAddResponseState.value.error != null -> {
            Text(text = "${productAddResponseState.value.error}")
        }

    }
    val scrollState = rememberScrollState()
    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CategoryHeader(titleText = "Product Add") {
                bottomNavController.navigateUp()
            }

            Spacer(modifier = Modifier.height(10.dp))

            AddItemTextField(
                modifier = Modifier.focusRequester(focusRequester),
                text = productAddScreenState.value.productName.value,
                titleText = "Medicine Name",
                onValueChange = {
                    productAddScreenState.value.productName.value = it
                },
                icon = Icons.Filled.Medication
            )

            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = productAddScreenState.value.productCategory.value,
                titleText = "Medicine Category",
                onValueChange = {
                    productAddScreenState.value.productCategory.value = it
                },
                icon = Icons.Filled.Category
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = productAddScreenState.value.productPrice.value,
                titleText = "Medicine Price",
                onValueChange = {
                    productAddScreenState.value.productPrice.value = it
                },
                icon = Icons.Filled.CurrencyRupee
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = productAddScreenState.value.productDescription.value,
                titleText = "Medicine Description",
                onValueChange = {
                    productAddScreenState.value.productDescription.value = it
                },
                icon = Icons.Filled.Description
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = productAddScreenState.value.productPower.value,
                titleText = "Medicine Power",
                onValueChange = {
                    productAddScreenState.value.productPower.value = it
                },
                icon = Icons.Filled.MedicalServices
            )

            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = productAddScreenState.value.productRating.value,
                titleText = "Medicine Rating",
                onValueChange = {
                    productAddScreenState.value.productRating.value = it
                },
                icon = Icons.Filled.StarRate
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = productAddScreenState.value.productStock.value,
                titleText = "Medicine Stock",
                onValueChange = {
                    productAddScreenState.value.productStock.value = it
                },
                icon = Icons.Filled.ProductionQuantityLimits
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = productAddScreenState.value.productExpiryDate.value,
                titleText = "Medicine Expiry Date",
                onValueChange = {
                    productAddScreenState.value.productExpiryDate.value = it
                },
                icon = Icons.Filled.CalendarMonth
            )

            Spacer(modifier = Modifier.height(5.dp))
            SelectItemImageSection(productAddScreenState)

            Spacer(modifier = Modifier.height(10.dp))
            AddItemButton("Add Product") {

                if (isInteger(productAddScreenState.value.productPrice.value) && isInteger(
                        productAddScreenState.value.productStock.value
                    ) && isFloat(productAddScreenState.value.productRating.value)
                ) {

                    medicalViewmodel.addProduct(
                        productName = productAddScreenState.value.productName.value,
                        productCategory = productAddScreenState.value.productCategory.value,
                        productPrice = productAddScreenState.value.productPrice.value.toInt(),
                        productDescription = productAddScreenState.value.productDescription.value,
                        productImage = productAddScreenState.value.productImage.value.toString(),
                        productPower = productAddScreenState.value.productPrice.value,
                        productRating = productAddScreenState.value.productRating.value.toFloat(),
                        productStock = productAddScreenState.value.productStock.value.toInt(),
                        productExpiryDate = productAddScreenState.value.productExpiryDate.value
                    )
                } else {
                    Toast.makeText(context, "Please enter valid data", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}

@Composable
fun AddItemButton(buttonText: String, addItemOnClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = addItemOnClick,
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenColor
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 1.dp, pressedElevation = 2.dp, disabledElevation = 0.dp
            )
        ) {
            Text(
                text = buttonText, style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    color = Color.White
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SelectItemImageSection(productAddScreenState: State<ProductAddScreenState>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
                onResult = {
                    productAddScreenState.value.productImage.value = it
                })
        Row(
            modifier = Modifier
                .shadow(1.dp, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Select Medicine Image", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            )

            IconButton(onClick = {
                launcher.launch("image/*")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_round),
                    contentDescription = "",
                    tint = GreenColor
                )
            }
        }
        productAddScreenState.value.productImage.value?.let {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = rememberImagePainter(productAddScreenState.value.productImage.value),
                contentDescription = "",
                modifier = Modifier
                    .height(110.dp)
                    .width(175.dp)
            )
        }
    }
}

@Composable
fun AddItemTextField(
    modifier: Modifier = Modifier,
    text: String,
    titleText: String,
    icon: ImageVector,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(0.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_medium))),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (titleText == "400") KeyboardType.Number else KeyboardType.Text
        ),
        placeholder = {
            Text(
                titleText, style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    textAlign = TextAlign.Center
                ), textAlign = TextAlign.Center
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon, contentDescription = null, tint = GreenColor
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = GreenColor,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = GreenColor
        ),

        readOnly = if (titleText == "title text") true else false,
    )
}

@Composable
fun CategoryHeader(titleText: String, backOnClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack,
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    backOnClick()
                })
        Text(
            text = titleText, style = TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            ), modifier = Modifier.weight(0.9f)
        )
    }
}