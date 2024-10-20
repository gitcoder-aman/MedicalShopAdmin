package com.tech.mymedicalstoreadminapp.domain.repository

import android.net.Uri
import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface MedicalRepository {

    suspend fun getAllUsers(): Flow<MedicalResponseState<Response<ArrayList<GetAllUsersResponseItem>>>>

    suspend fun doApprovedUser(
        userId: String,
        isApproved: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun addProduct(
        productName: RequestBody,
        productCategory: RequestBody,
        productPrice: RequestBody,
        productStock: RequestBody,
        productExpiryDate: RequestBody,
        productRating: RequestBody,
        productDescription: RequestBody,
        productImage: MultipartBody.Part,
        productPower: RequestBody,
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun getAllOrders(): Flow<MedicalResponseState<Response<ArrayList<MedicalOrderResponseItem>>>>
    suspend fun doApprovedOrder(
        orderId: String,
        isApprovedOrder: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>
}