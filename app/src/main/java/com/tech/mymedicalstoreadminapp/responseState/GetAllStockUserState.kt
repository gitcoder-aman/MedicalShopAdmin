package com.tech.mymedicalstoreadminapp.responseState

import com.tech.mymedicalstoreadminapp.data.response.user.UserStockResponseItem
import retrofit2.Response

data class GetAllStockUserState(
    val loading : Boolean = false,
    val data : Response<ArrayList<UserStockResponseItem>> ?= null,
    val error : String ?= null
)
