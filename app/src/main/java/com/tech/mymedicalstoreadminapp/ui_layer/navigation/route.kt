package com.tech.mymedicalstoreadminapp.ui_layer.navigation

import kotlinx.serialization.Serializable

@Serializable
data object DashboardScreenRoute {
    const val route: String = "home"
}

@Serializable
data object ApproveUserScreenRoute {
    const val route: String = "approve_user"
}

@Serializable
data object ApproveOrderScreenRoute {
    const  val route: String = "approve_order"
}
@Serializable
data class OrderDetailScreenRoute(
    val orderId : String
) {
      val route: String = "order_details/$orderId"
}

@Serializable
data object AllProductsScreenRoute {
    const val route: String = "all_product"
}
@Serializable
data object AllStockScreenRoute {
    const val route: String = "all_stock"
}
@Serializable
data object SellHistoryScreenRoute {
    const val route: String = "sell_history"
}

@Serializable
data class ProductDetailScreenRoute(
    val productId: String
) {
     val route: String = "product_detail/$productId"
}

@Serializable
data object AddProductScreenRoute {
    const val route: String = "add_product"
}
@Serializable
data object UpdateProductScreenRoute {
    const val route: String = "update_product"
}


