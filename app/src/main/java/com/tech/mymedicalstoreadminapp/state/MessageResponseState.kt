package com.tech.mymedicalstoreadminapp.state

import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import retrofit2.Response

data class MessageResponseState(
    val isLoading: Boolean = false,
    val data: Response<MessageStatusResponse>? = null,
    val error: String ?= null
)
