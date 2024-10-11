package com.tech.mymedicalstoreadminapp.responseState

import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import retrofit2.Response

data class GetAllUserState(
    val loading: Boolean = false,
    val data: Response<ArrayList<GetAllUsersResponseItem>> ?= null,
    val error: String ?= null
)
