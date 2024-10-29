package com.tech.mymedicalstoreadminapp.responseState

import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import retrofit2.Response

data class AddUpdateProductState(
    val loading : Boolean = false,
    val data : Response<MessageStatusResponse> ?= null,
    val error : String ?= null
)
