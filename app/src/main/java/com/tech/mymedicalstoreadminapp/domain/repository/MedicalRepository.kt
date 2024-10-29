package com.tech.mymedicalstoreadminapp.domain.repository

import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import com.tech.mymedicalstoreadminapp.data.response.product.ProductModelItem
import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import com.tech.mymedicalstoreadminapp.data.response.user.SellHistoryResponseItem
import com.tech.mymedicalstoreadminapp.data.response.user.UserStockResponseItem
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
        isApprovedOrder: Int,
        orderStatusStep: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun updateShippedOrder(
        orderId: String,
        shippedDate: String,
        orderStatusStep: String
    ) : Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun updateOutOfDeliveryOrder(
        orderId: String,
        outOfDeliveryDate: String,
        orderStatusStep: String
    ) : Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun updateDeliveredOrder(
        orderId: String,
        deliveredDate: String,
        orderStatusStep: String
    ) : Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun updateCancelOrder(
        orderId: String,
        cancelStatus: String,
        orderStatusStep: String
    ) : Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun getAllProducts(): Flow<MedicalResponseState<Response<ArrayList<ProductModelItem>>>>

    suspend fun getSpecificProduct(
        productId : String
    ):Flow<MedicalResponseState<Response<ArrayList<ProductModelItem>>>>

    suspend fun updateProduct(
        productId: RequestBody,
        productName: RequestBody,
        productImage: MultipartBody.Part,
        productCategory: RequestBody,
        productPrice: RequestBody,
        productStock: RequestBody,
        productExpiryDate: RequestBody,
        productRating: RequestBody,
        productDescription: RequestBody,
        productPower: RequestBody

    ):Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun deleteProduct(
        productId : String
    ):Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun deleteUser(
        userId : String
    ):Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun updateBlockStatus(
        userId: String,
        isBlock: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun getSpecificOrder(
        orderId : String
    ):Flow<MedicalResponseState<Response<ArrayList<MedicalOrderResponseItem>>>>

    suspend fun updateStockProduct(
        productId: String,
        productStock: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun addOrderInUserStock(
        userId : String,
        orderId : String,
        productId : String,
        productName : String,
        userName : String,
        certified : Boolean,
        productStock : Int,
        productPrice : Int,
        productCategory : String
    ) : Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun addInSellHistory(
        userId: String,
        productId: String,
        quantity: String,
        remainingStock: String,
        dateOfSell: String,
        totalAmount: String,
        productPrice: String,
        productName: String,
        productCategory: String,
        userName: String,
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>>

    suspend fun getAllSellHistory(): Flow<MedicalResponseState<Response<ArrayList<SellHistoryResponseItem>>>>

    suspend fun getAllUserStock(): Flow<MedicalResponseState<Response<ArrayList<UserStockResponseItem>>>>

}