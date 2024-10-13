package com.tech.mymedicalstoreadminapp.responseState

import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import retrofit2.Response

data class GetAllOrderState(
    val loading : Boolean = false,
    val data : Response<ArrayList<MedicalOrderResponseItem>>? = null,
    val error : String ?= null
)
