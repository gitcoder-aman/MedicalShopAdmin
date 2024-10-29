package com.tech.mymedicalstoreadminapp.ui_layer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tech.mymedicalstoreadminapp.ui_layer.screen.DashboardScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.all_user_screen.ApproveUserScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.order_screen.ApproveOrderScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.order_screen.OrderDetailScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.product_screen.AllProductShowScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.product_screen.ProductAddScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.product_screen.ProductDetailScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.product_screen.ProductUpdateScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.sell_history_screen.SellHistoryScreen
import com.tech.mymedicalstoreadminapp.ui_layer.screen.stock_screen.AllStockScreen
import com.tech.mymedicalstoreadminapp.viewmodel.UserViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.OrderViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.ProductViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.SellHistoryViewmodel
import com.tech.mymedicalstoreadminapp.viewmodel.UserStockViewmodel

@Composable
fun NavGraph(navController: NavHostController, bottomPadding: Dp) {

    val userViewmodel: UserViewmodel = hiltViewModel()
    val orderViewmodel: OrderViewmodel = hiltViewModel()
    val productViewmodel: ProductViewmodel = hiltViewModel()
    val userStockViewmodel: UserStockViewmodel = hiltViewModel()
    val sellHistoryViewmodel: SellHistoryViewmodel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = DashboardScreenRoute.route,
        modifier = Modifier.padding(bottom = bottomPadding)
    ) {
        composable(DashboardScreenRoute.route) {
            DashboardScreen(navController)
        }
        composable(ApproveUserScreenRoute.route) {
            ApproveUserScreen(userViewmodel)
        }
        composable(ApproveOrderScreenRoute.route) {
            ApproveOrderScreen(orderViewmodel, navController)
        }
        composable("order_details/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")
            if (orderId != null) {
                OrderDetailScreen(
                    orderId = orderId,
                    navController = navController,
                    orderViewmodel = orderViewmodel,
                    productViewmodel = productViewmodel,
                    userStockViewmodel =userStockViewmodel,
                    sellHistoryViewmodel =sellHistoryViewmodel
                )
            }
        }
        composable(AllProductsScreenRoute.route) {
            AllProductShowScreen(productViewmodel, navController)
        }
        composable(AllStockScreenRoute.route) {
            AllStockScreen(navController,userStockViewmodel)
        }
        composable(SellHistoryScreenRoute.route) {
            SellHistoryScreen(navController,sellHistoryViewmodel)
        }
        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                ProductDetailScreen(
                    navController = navController,
                    productViewmodel = productViewmodel,
                    productId = productId
                )
            }
        }
        composable(AddProductScreenRoute.route) {
            ProductAddScreen(
                navController = navController,
                productViewmodel = productViewmodel
            )
        }
        composable(UpdateProductScreenRoute.route) {
            ProductUpdateScreen(
                navController = navController,
                productViewmodel = productViewmodel
            )
        }
    }

}