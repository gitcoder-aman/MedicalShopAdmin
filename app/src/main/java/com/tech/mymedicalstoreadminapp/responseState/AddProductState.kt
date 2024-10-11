package com.tech.mymedicalstoreadminapp.responseState

import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import retrofit2.Response

data class AddProductState(
    val loading : Boolean = false,
    val data : Response<MessageStatusResponse> ?= null,
    val error : String ?= null
)
