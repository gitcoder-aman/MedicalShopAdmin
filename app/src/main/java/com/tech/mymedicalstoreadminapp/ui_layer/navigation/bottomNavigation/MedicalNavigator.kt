package com.tech.mymedicalstoreadminapp.ui_layer.navigation.bottomNavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.AddProductScreenRoute
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.AllProductsScreenRoute
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.ApproveOrderScreenRoute
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.ApproveUserScreenRoute
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.DashboardScreenRoute
import com.tech.mymedicalstoreadminapp.ui_layer.navigation.NavGraph

@Composable
fun MedicalNavigator(
) {

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            title = "Dashboard",
            selectedIcon = Icons.Default.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard,
        ),
        BottomNavigationItem(
            title = "Approve Users",
            selectedIcon = Icons.Default.Person,
            unselectedIcon = Icons.Outlined.Person
        ),
        BottomNavigationItem(
            title = "Approve Orders",
            selectedIcon = Icons.Default.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart
        ),
        BottomNavigationItem(
            title = "All Products",
            selectedIcon = Icons.Default.MedicalServices,
            unselectedIcon = Icons.Outlined.MedicalServices,
        )
    )
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Update selectedItem based on backStackEntry
    if (backStackEntry.value != null) {
        selectedItem = when (backStackEntry.value?.destination?.route) {
            DashboardScreenRoute.route -> 0
            ApproveUserScreenRoute.route -> 1
            ApproveOrderScreenRoute.route -> 2
            AllProductsScreenRoute.route -> 3
            else -> 0
        }
    }
    // Update selectedItem based on backStackEntry
    backStackEntry.value?.let { entry ->
        selectedItem = when (entry.destination.route) {
            DashboardScreenRoute.route -> 0
            ApproveUserScreenRoute.route -> 1
            ApproveOrderScreenRoute.route -> 2
            AllProductsScreenRoute.route -> 3
            else -> 0
        }
    }
    val isBottomBarVisible = backStackEntry.value?.destination?.route in listOf(
        DashboardScreenRoute.route,
        ApproveUserScreenRoute.route,
        ApproveOrderScreenRoute.route,
        AllProductsScreenRoute.route
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                MedicalBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItemIndex = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = DashboardScreenRoute.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = ApproveUserScreenRoute.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = ApproveOrderScreenRoute.route
                            )
                            3-> navigateToTab(
                                navController = navController,
                                route = AllProductsScreenRoute.route
                            )
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            if (isBottomBarVisible) {
                FloatingActionButton(onClick = {
                    navController.navigate(AddProductScreenRoute.route)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Product")
                }
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavGraph(navController, bottomPadding)
    }

}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}