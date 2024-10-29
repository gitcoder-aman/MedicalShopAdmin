package com.tech.mymedicalstoreadminapp.responseState

import com.tech.mymedicalstoreadminapp.data.response.user.SellHistoryResponseItem
import retrofit2.Response

data class GetAllSellHistoryState(
    val loading : Boolean = false,
    val data : Response<ArrayList<SellHistoryResponseItem>> ?= null,
    val error : String ?= null
)
