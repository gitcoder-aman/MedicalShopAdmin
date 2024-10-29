package com.tech.mymedicalstoreadminapp.data.response.user

data class UserStockResponseItem(
    val certified: String,
    val id: Int,
    val product_category: String,
    val product_id: String,
    val product_name: String,
    val product_price: Int,
    val product_stock: String,
    val user_id: String,
    val order_id: String,
    val user_name: String
)