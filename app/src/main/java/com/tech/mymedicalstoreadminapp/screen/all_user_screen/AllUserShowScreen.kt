package com.tech.mymedicalstoreadminapp.screen.all_user_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tech.mymedicalstoreadminapp.navigation.ProductAddScreenRoute
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.viewmodel.MedicalViewmodel

@Composable
fun AllUserShowScreen(
    mainViewmodel: MedicalViewmodel = hiltViewModel()
) {

    val getAllUserResponseState = mainViewmodel.getAllUserResponseState.collectAsState()
    val isApprovedUserResponseState = mainViewmodel.isApprovedUserResponseState.collectAsState()
    val getAllUsersData = getAllUserResponseState.value.data?.body() ?: emptyList()
    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    when {
        isApprovedUserResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        isApprovedUserResponseState.value.data != null -> {
            LaunchedEffect(key1 = getAllUserResponseState) {
                mainViewmodel.getAllUsers()
                Toast.makeText(
                    context,
                    "${isApprovedUserResponseState.value.data}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        isApprovedUserResponseState.value.error != null -> {
            Toast.makeText(
                context,
                "${isApprovedUserResponseState.value.error}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    when {
        getAllUserResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        getAllUserResponseState.value.data != null -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = lazyState
                ) {
                    items(getAllUsersData) { userData ->
                        UserCardView(userData, approveOnClick = {
                            mainViewmodel.doApprovedUser(
                                userId = userData.user_id,
                                isApproved = 1
                            )
                            Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show()
                        }, disApproveOnClick = {
                            mainViewmodel.doApprovedUser(
                                userId = userData.user_id,
                                isApproved = 0
                            )
                            Toast.makeText(context, "DisApproved", Toast.LENGTH_SHORT).show()
                        })
                    }

            }

            Log.d("TAG", "AllUserShowScreen: ${getAllUserResponseState.value.data}")
        }

        getAllUserResponseState.value.error != null -> {
            Toast.makeText(context, "${getAllUserResponseState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }

    }
}