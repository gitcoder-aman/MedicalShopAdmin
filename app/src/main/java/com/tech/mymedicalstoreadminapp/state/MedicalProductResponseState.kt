package com.tech.mymedicalstoreadminapp.state

import com.tech.mymedicalstoreadminapp.data.response.product.ProductModelItem


data class MedicalProductResponseState(
    val isLoading : Boolean = false,
    val data : ArrayList<ProductModelItem>? = null,
    val error : String ?= null
)
