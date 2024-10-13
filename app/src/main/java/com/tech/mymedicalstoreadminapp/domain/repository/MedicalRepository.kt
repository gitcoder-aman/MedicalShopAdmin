package com.tech.mymedicalstoreadminapp.domain.repository

import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MedicalRepository {

    suspend fun getAllUsers(): Flow<MedicalResponseState<Response<ArrayList<GetAllUsersResponseItem>>>>

    suspend fun doApprovedUser(
        userId: String,
        isApproved: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun addProduct(
        productName: String,
        productCategory: String,
        productPrice: Int,
        productStock: Int,
        productExpiryDate: String,
        productRating: Float,
        productDescription: String,
        productImage: String,
        productPower: String,
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun getAllOrders(): Flow<MedicalResponseState<Response<ArrayList<MedicalOrderResponseItem>>>>
    suspend fun doApprovedOrder(
        orderId: String,
        isApprovedOrder: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>
}