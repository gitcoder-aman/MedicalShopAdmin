package com.tech.mymedicalstoreadminapp.ui_layer.screen.product_screen

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.tech.mymedicalstoreadminapp.utils.isFloat
import com.tech.mymedicalstoreadminapp.utils.isInteger
import com.tech.mymedicalstoreadminapp.R
import com.tech.mymedicalstoreadminapp.state.screen_state.ProductAddUpdateScreenState
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.ui.theme.darkWhiteColor
import com.tech.mymedicalstoreadminapp.utils.getFileFromUri
import com.tech.mymedicalstoreadminapp.viewmodel.ProductViewmodel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@Composable
fun ProductUpdateScreen(
    navController: NavHostController,
    productViewmodel: ProductViewmodel
) {

    val context = LocalContext.current
    val updateProductResponseState = productViewmodel.updateProductResponseData.collectAsState()
    val updateProductScreenState = productViewmodel.updateProductScreenData.collectAsState()

    when {
        updateProductResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        updateProductResponseState.value.data != null -> {
            if (updateProductResponseState.value.data!!.body()?.status == 200) {
                LaunchedEffect(Unit) {
                    productViewmodel.resetProductUpdateScreenState()
                    navController.navigateUp()
                    Toast.makeText(
                        context,
                        updateProductResponseState.value.data!!.body()?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Toast.makeText(context, "Product Updated Successfully", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }

        updateProductResponseState.value.error != null -> {
            Log.d("@product", "ProductAddScreen: ${updateProductResponseState.value.error}")
            Toast.makeText(context, updateProductResponseState.value.error, Toast.LENGTH_SHORT)
                .show()
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
            CategoryHeader(titleText = "Update Product") {
                navController.navigateUp()
            }

            Spacer(modifier = Modifier.height(10.dp))

            AddItemTextField(
                modifier = Modifier.focusRequester(focusRequester),
                text = updateProductScreenState.value.productName.value,
                titleText = "Medicine Name",
                onValueChange = {
                    updateProductScreenState.value.productName.value = it
                },
                icon = Icons.Filled.Medication
            )

            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = updateProductScreenState.value.productCategory.value,
                titleText = "Medicine Category",
                onValueChange = {
                    updateProductScreenState.value.productCategory.value = it
                },
                icon = Icons.Filled.Category
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = updateProductScreenState.value.productPrice.value,
                titleText = "Medicine Price",
                onValueChange = {
                    updateProductScreenState.value.productPrice.value = it
                },
                icon = Icons.Filled.CurrencyRupee
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = updateProductScreenState.value.productDescription.value,
                titleText = "Medicine Description",
                onValueChange = {
                    updateProductScreenState.value.productDescription.value = it
                },
                icon = Icons.Filled.Description
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = updateProductScreenState.value.productPower.value,
                titleText = "Medicine Power",
                onValueChange = {
                    updateProductScreenState.value.productPower.value = it
                },
                icon = Icons.Filled.MedicalServices
            )

            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = updateProductScreenState.value.productRating.value,
                titleText = "Medicine Rating",
                onValueChange = {
                    updateProductScreenState.value.productRating.value = it
                },
                icon = Icons.Filled.StarRate
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = updateProductScreenState.value.productStock.value,
                titleText = "Medicine Stock",
                onValueChange = {
                    updateProductScreenState.value.productStock.value = it
                },
                icon = Icons.Filled.ProductionQuantityLimits
            )
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = updateProductScreenState.value.productExpiryDate.value,
                titleText = "Medicine Expiry Date",
                onValueChange = {
                    updateProductScreenState.value.productExpiryDate.value = it
                },
                icon = Icons.Filled.CalendarMonth
            )

            Spacer(modifier = Modifier.height(5.dp))
            SelectItemImageUpdateSection(updateProductScreenState)

            Spacer(modifier = Modifier.height(10.dp))
            UpdateItemButton("Update Product") {

                if (updateProductScreenState.value.productImage.value != null) {

                    val imageFile =
                        getFileFromUri(context, updateProductScreenState.value.productImage.value!!)

                    if (imageFile != null && imageFile.exists()) {
                        val requestFile = imageFile.asRequestBody("image/jpg".toMediaTypeOrNull())
                        val body =
                            MultipartBody.Part.createFormData("pic", imageFile.name, requestFile)

                        if (isInteger(updateProductScreenState.value.productPrice.value) && isInteger(
                                updateProductScreenState.value.productStock.value
                            ) && isFloat(updateProductScreenState.value.productRating.value)
                        ) {

                            productViewmodel.updateProduct(
                                productId = updateProductScreenState.value.productId.value,
                                productImageFile = body
                            )
                        } else {
                            Toast.makeText(context, "Please enter valid data", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Log.e("UploadError", "Image file not found or could not be accessed.")
                    }
                } else {
                    val body = MultipartBody.Part.createFormData(
                        "pic",
                        "-1",
                    )
                    if (isInteger(updateProductScreenState.value.productPrice.value) && isInteger(
                            updateProductScreenState.value.productStock.value
                        ) && isFloat(updateProductScreenState.value.productRating.value)
                    ) {
                        productViewmodel.updateProduct(
                            productId = updateProductScreenState.value.productId.value,
                            productImageFile = body
                        )
                    } else {
                        Toast.makeText(context, "Please enter valid data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }
    }
}

@Composable
fun UpdateItemButton(buttonText: String, addItemOnClick: () -> Unit) {
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

@Composable
fun SelectItemImageUpdateSection(updateProductScreenState: State<ProductAddUpdateScreenState>) {

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
                    updateProductScreenState.value.productImage.value = it
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
        updateProductScreenState.value.productImage.value?.let {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = rememberAsyncImagePainter(updateProductScreenState.value.productImage.value),
                contentDescription = "",
                modifier = Modifier
                    .height(110.dp)
                    .width(175.dp)
            )
        }
    }
}