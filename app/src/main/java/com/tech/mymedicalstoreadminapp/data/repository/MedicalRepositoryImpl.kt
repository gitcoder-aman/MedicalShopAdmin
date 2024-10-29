package com.tech.mymedicalstoreadminapp.data.repository

import com.tech.mymedicalstoreadminapp.data.response.order.MedicalOrderResponseItem
import com.tech.mymedicalstoreadminapp.data.response.product.ProductModelItem
import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import com.tech.mymedicalstoreadminapp.data.response.user.SellHistoryResponseItem
import com.tech.mymedicalstoreadminapp.data.response.user.UserStockResponseItem
import com.tech.mymedicalstoreadminapp.data.services.ApiServices
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class MedicalRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : MedicalRepository {
    override suspend fun getAllUsers(): Flow<MedicalResponseState<Response<ArrayList<GetAllUsersResponseItem>>>> =
        flow {
            emit(MedicalResponseState.Loading)
            try {
                val response = apiServices.getAllUsers()
                emit(MedicalResponseState.Success(response))
            } catch (e: Exception) {
                emit(MedicalResponseState.Error(e.message.toString()))
            }
        }


    override suspend fun doApprovedUser(
        userId: String,
        isApproved: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateApproveStatus(userId = userId, isApproved = isApproved)
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun addProduct(
        productName: RequestBody,
        productCategory: RequestBody,
        productPrice: RequestBody,
        productStock: RequestBody,
        productExpiryDate: RequestBody,
        productRating: RequestBody,
        productDescription: RequestBody,
        productImage: MultipartBody.Part,
        productPower: RequestBody
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)

        try {
            val response = apiServices.addProduct(
                productName = productName,
                productCategory = productCategory,
                productPrice = productPrice,
                productStock = productStock,
                productExpiryDate = productExpiryDate,
                productRating = productRating,
                productDescription = productDescription,
                pic = productImage,
                productPower = productPower
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun getAllOrders(): Flow<MedicalResponseState<Response<ArrayList<MedicalOrderResponseItem>>>> =
        flow {
            emit(MedicalResponseState.Loading)
            try {
                val response = apiServices.getAllOrders()
                emit(MedicalResponseState.Success(response))
            } catch (e: Exception) {
                emit(MedicalResponseState.Error(e.message.toString()))
            }
        }

    override suspend fun doApprovedOrder(
        orderId: String,
        isApprovedOrder: Int,
        orderStatusStep: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.doApprovedOrder(
                orderId = orderId,
                isApprovedOrder = isApprovedOrder,
                orderStatusStep = orderStatusStep
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun updateShippedOrder(
        orderId: String,
        shippedDate: String,
        orderStatusStep: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateShippedOrder(
                orderId = orderId,
                shippedDate = shippedDate,
                orderStatusStep = orderStatusStep
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun updateOutOfDeliveryOrder(
        orderId: String,
        outOfDeliveryDate: String,
        orderStatusStep: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateOutOfDeliveryOrder(
                orderId = orderId,
                outOfDeliveryDate = outOfDeliveryDate,
                orderStatusStep = orderStatusStep
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun updateDeliveredOrder(
        orderId: String,
        deliveredDate: String,
        orderStatusStep: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateDeliveredOrder(
                orderId = orderId,
                deliveredDate = deliveredDate,
                orderStatusStep = orderStatusStep
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun updateCancelOrder(
        orderId: String,
        cancelStatus: String,
        orderStatusStep: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateCancelledOrder(
                orderId = orderId,
                orderCancelStatus = cancelStatus,
                orderStatusStep = orderStatusStep
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun getAllProducts(): Flow<MedicalResponseState<Response<ArrayList<ProductModelItem>>>> =
        flow {
            emit(MedicalResponseState.Loading)
            try {
                val response = apiServices.getAllProduct()
                emit(MedicalResponseState.Success(response))
            } catch (e: Exception) {
                emit(MedicalResponseState.Error(e.message.toString()))
            }
        }

    override suspend fun getSpecificProduct(
        productId: String
    ): Flow<MedicalResponseState<Response<ArrayList<ProductModelItem>>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.getSpecificProduct(productId)
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun updateProduct(
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
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateProduct(
                productId = productId,
                pic = productImage,
                productName = productName,
                productCategory = productCategory,
                productPrice = productPrice,
                productStock = productStock,
                productExpiryDate = productExpiryDate,
                productRating = productRating,
                productDescription = productDescription,
                productPower = productPower
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun deleteProduct(productId: String): Flow<MedicalResponseState<Response<MessageStatusResponse>>> =
        flow {
            emit(MedicalResponseState.Loading)
            try {
                val response = apiServices.deleteProduct(productId)
                emit(MedicalResponseState.Success(response))
            } catch (e: Exception) {
                emit(MedicalResponseState.Error(e.message.toString()))
            }
        }

    override suspend fun deleteUser(userId: String): Flow<MedicalResponseState<Response<MessageStatusResponse>>> =
        flow {
            emit(MedicalResponseState.Loading)
            try {
                val response = apiServices.deleteUser(userId)
                emit(MedicalResponseState.Success(response))
            } catch (e: Exception) {
                emit(MedicalResponseState.Error(e.message.toString()))
            }
        }

    override suspend fun updateBlockStatus(
        userId: String,
        isBlock: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateBlockStatus(
                userId = userId,
                isBlock = isBlock
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }

    }

    override suspend fun getSpecificOrder(orderId: String): Flow<MedicalResponseState<Response<ArrayList<MedicalOrderResponseItem>>>> =
        flow {
            emit(MedicalResponseState.Loading)
            try {
                val response = apiServices.getSpecificOrder(orderId)
                emit(MedicalResponseState.Success(response))
            } catch (e: Exception) {
                emit(MedicalResponseState.Error(e.message.toString()))
            }
        }

    override suspend fun updateStockProduct(
        productId: String,
        productStock: Int
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.updateStockProduct(
                productId = productId,
                productStock = productStock
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun addOrderInUserStock(
        userId: String,
        orderId: String,
        productId: String,
        productName: String,
        userName: String,
        certified: Boolean,
        productStock: Int,
        productPrice: Int,
        productCategory: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.addOrderInUserStock(
                userId = userId,
                orderId = orderId,
                productId = productId,
                productName = productName,
                userName = userName,
                certified = certified,
                productStock = productStock,
                productPrice = productPrice,
                productCategory = productCategory
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun addInSellHistory(
        userId: String,
        productId: String,
        quantity: String,
        remainingStock: String,
        dateOfSell: String,
        totalAmount: String,
        productPrice: String,
        productName: String,
        productCategory: String,
        userName: String
    ): Flow<MedicalResponseState<Response<MessageStatusResponse>>> = flow {
        emit(MedicalResponseState.Loading)

        try {
            val response = apiServices.addSellHistory(
                userId = userId,
                productId = productId,
                quantity = quantity,
                remainingStock = remainingStock,
                dateOfSell = dateOfSell,
                totalAmount = totalAmount,
                productPrice = productPrice,
                productName = productName,
                productCategory = productCategory,
                userName = userName
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(
                MedicalResponseState.Error(e.message.toString())
            )
        }
    }

    override suspend fun getAllSellHistory(): Flow<MedicalResponseState<Response<ArrayList<SellHistoryResponseItem>>>> = flow {
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.getAllSellHistory()
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun getAllUserStock(): Flow<MedicalResponseState<Response<ArrayList<UserStockResponseItem>>>> = flow{
        emit(MedicalResponseState.Loading)
        try {
            val response = apiServices.getAllUserStock()
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }
}