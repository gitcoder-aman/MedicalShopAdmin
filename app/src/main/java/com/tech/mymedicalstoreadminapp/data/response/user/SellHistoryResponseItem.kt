package com.tech.mymedicalstoreadminapp.data.response.user

data class SellHistoryResponseItem(
    val date_of_sell: String,
    val id: Int,
    val price: String,
    val product_category: String,
    val product_id: String,
    val product_name: String,
    val quantity: String,
    val remaining_stock: String,
    val sell_id: String,
    val total_amount: String,
    val user_id: String,
    val user_name: String
)