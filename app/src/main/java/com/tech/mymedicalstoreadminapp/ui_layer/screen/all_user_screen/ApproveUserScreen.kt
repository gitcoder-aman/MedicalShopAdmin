package com.tech.mymedicalstoreadminapp.ui_layer.screen.all_user_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import com.tech.mymedicalstoreadminapp.ui.theme.GreenColor
import com.tech.mymedicalstoreadminapp.ui_layer.component.AlertDialogComponent
import com.tech.mymedicalstoreadminapp.viewmodel.UserViewmodel

@Composable
fun ApproveUserScreen(userViewmodel: UserViewmodel) {

    val getAllUserResponseState = userViewmodel.getAllUserResponseState.collectAsState()
    val isApprovedUserResponseState = userViewmodel.isApprovedUserResponseState.collectAsState()
    val isBlockedUserResponseState = userViewmodel.isBlockedUserResponseState.collectAsState()
    val isDeleteUserResponseState = userViewmodel.isDeleteUserResponseState.collectAsState()
    val getAllUsersData = getAllUserResponseState.value.data?.body() ?: emptyList()
    val context = LocalContext.current
    val lazyState = rememberLazyListState()
    var showDialog by remember { mutableStateOf(false) }
    var userId by remember { mutableStateOf("") }


    when {
        isApprovedUserResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        isApprovedUserResponseState.value.data != null -> {
            if (isApprovedUserResponseState.value.data!!.body()?.status == 200) {
                LaunchedEffect(key1 = getAllUserResponseState) {
                    userViewmodel.getAllUsers()
                    userViewmodel.resetUserState()
                    Toast.makeText(
                        context,
                        isApprovedUserResponseState.value.data!!.body()?.message,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
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
        isBlockedUserResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        isBlockedUserResponseState.value.data != null -> {
            if (isBlockedUserResponseState.value.data!!.body()?.status == 200) {
                LaunchedEffect(key1 = getAllUserResponseState) {
                    userViewmodel.getAllUsers()
                    userViewmodel.resetUserState()
                    Toast.makeText(
                        context,
                        isBlockedUserResponseState.value.data!!.body()?.message,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }

        isBlockedUserResponseState.value.error != null -> {
            Toast.makeText(
                context,
                "${isBlockedUserResponseState.value.error}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    when {
        isDeleteUserResponseState.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading...")
            }
        }

        isDeleteUserResponseState.value.data != null -> {
            if (isDeleteUserResponseState.value.data!!.body()?.status == 200) {
                LaunchedEffect(key1 = getAllUserResponseState) {
                    userViewmodel.getAllUsers()
                    userViewmodel.resetUserState()
                    Toast.makeText(
                        context,
                        isDeleteUserResponseState.value.data!!.body()?.message,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }

        isDeleteUserResponseState.value.error != null -> {
            Toast.makeText(
                context,
                "${isDeleteUserResponseState.value.error}",
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
                items(getAllUsersData.reversed()) { userData ->
                    UserCardView(
                        userData,
                        approveOnClick = {
                            userViewmodel.doApprovedUser(
                                userId = userData.user_id,
                                isApproved = 1
                            )
                            Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show()
                        },
                        disApproveOnClick = {
                            userViewmodel.doApprovedUser(
                                userId = userData.user_id,
                                isApproved = 0
                            )
                            Toast.makeText(context, "DisApproved", Toast.LENGTH_SHORT).show()
                        },

                        //nothing to effect on user side when click on block and unblock
                        blockOnClick = {
                            userViewmodel.updateBlockUser(
                                userId = userData.user_id,
                                isBlocked = 1
                            )
                            Toast.makeText(context, "Blocked", Toast.LENGTH_SHORT).show()
                        },
                        unblockOnClick = {
                            userViewmodel.updateBlockUser(
                                userId = userData.user_id,
                                isBlocked = 0
                            )
                            Toast.makeText(context, "UnBlocked", Toast.LENGTH_SHORT).show()
                        },
                        deleteOnClick = {
                            showDialog = true
                            userId = userData.user_id
                        }
                    )
                }
            }

            Log.d("TAG", "AllUserShowScreen: ${getAllUserResponseState.value.data}")
        }

        getAllUserResponseState.value.error != null -> {
            Toast.makeText(context, "${getAllUserResponseState.value.error}", Toast.LENGTH_SHORT)
                .show()
        }

    }

    if (showDialog) {
        AlertDialogComponent(
            title = "Delete User",
            text = "Are you sure you want to delete this user?",
            onConfirm = {
                userViewmodel.deleteUser(userId = userId)
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}


