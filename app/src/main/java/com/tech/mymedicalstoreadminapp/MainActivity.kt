package com.tech.mymedicalstoreadminapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tech.mymedicalstoreadminapp.screen.all_user_screen.AllUserShowScreen
import com.tech.mymedicalstoreadminapp.screen.ApproveUserScreen
import com.tech.mymedicalstoreadminapp.screen.OrderHistoryScreen
import com.tech.mymedicalstoreadminapp.screen.OrderStatusScreen
import com.tech.mymedicalstoreadminapp.screen.ProductAddScreen
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val bottomNavController = rememberNavController()
            MyAppTheme {
                val items = listOf<BottomNavigationItem>(
                    BottomNavigationItem(
                        title = "All Users",
                        selectedIcon = Icons.Default.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        title = "Approve User",
                        selectedIcon = Icons.Default.Check,
                        unselectedIcon = Icons.Outlined.Check,
                        hasNews = true,
                        badgeCount = 10
                    ),
                    BottomNavigationItem(
                        title = "Order Status",
                        selectedIcon = Icons.Default.ShoppingCart,
                        unselectedIcon = Icons.Outlined.ShoppingCart,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        title = "Order History",
                        selectedIcon = Icons.Default.Refresh,
                        unselectedIcon = Icons.Outlined.Refresh,
                        hasNews = true,
                        badgeCount = 45
                    )
                )
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
//                                            //navigate here
                                            bottomNavController.navigate(item.title) {
                                                // Clear back stack for a new navigation
                                                popUpTo(bottomNavController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (item.badgeCount != null) {
                                                        Badge {
                                                            Text(text = item.badgeCount.toString())
                                                        }
                                                    } else {
                                                        if (item.hasNews) {
                                                            Badge()
                                                        }
                                                    }
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (index == selectedItemIndex) {
                                                        item.selectedIcon
                                                    } else {
                                                        item.unselectedIcon
                                                    }, contentDescription = item.title
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    bottomNavController.navigate("Add Product")
                                },
                                shape = CircleShape,
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Add")
                            }
                        },
                        floatingActionButtonPosition = FabPosition.End, // Position of the FAB
                    ) {
                        NavHost(
                            navController = bottomNavController,
                            startDestination = items[0].title,
                            Modifier.padding(it)
                        ) {
                            composable("All Users") { AllUserShowScreen() }
                            composable("Approve User") { ApproveUserScreen() }
                            composable("Order Status") { OrderStatusScreen() }
                            composable("Order History") { OrderHistoryScreen() }
                            composable("Add Product") { ProductAddScreen() }
                        }
                    }

                }
            }

        }
    }
}
