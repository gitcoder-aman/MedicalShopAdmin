package com.tech.mymedicalstoreadminapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.navigation.AllUserShowScreenRoute
import com.tech.mymedicalstoreadminapp.navigation.ProductAddScreenRoute
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.viewmodel.MedicalViewmodel

@Composable
fun ProductAddScreen(
    medicalViewmodel: MedicalViewmodel = hiltViewModel()
) {

    val context = LocalContext.current
    val productAddResponseState = medicalViewmodel.addProductResponseState.collectAsState()

    when {
        productAddResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        productAddResponseState.value.data != null -> {
            if (productAddResponseState.value.data!!.isSuccessful) {
                Toast.makeText(
                    context,
                    productAddResponseState.value.data!!.message(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }

        productAddResponseState.value.error != null -> {
            Text(text = "${productAddResponseState.value.error}")
        }

    }
    Text(text = "Product Add Screen",modifier = Modifier.clickable {
        medicalViewmodel.addProduct(
            productName = "Paracetamol",
            productCategory = "Medicine",
            productPrice = 100,
            productDescription = "Description",
            productImage = "Image",
            productPower = "500mg",
            productRating = 4.5f,
            productStock = 100,
            productExpiryDate = "2023-12-31"
        )
    })
}