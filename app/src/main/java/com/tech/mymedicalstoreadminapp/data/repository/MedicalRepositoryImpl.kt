package com.tech.mymedicalstoreadminapp.data.repository

import com.tech.mymedicalstoreadminapp.data.response.signupLogin.MessageStatusResponse
import com.tech.mymedicalstoreadminapp.data.response.user.GetAllUsersResponseItem
import com.tech.mymedicalstoreadminapp.data.services.ApiServices
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
        productName: String,
        productCategory: String,
        productPrice: Int,
        productStock: Int,
        productExpiryDate: String,
        productRating: Float,
        productDescription: String,
        productImage: String,
        productPower: String
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
                productImage = productImage,
                productPower = productPower
            )
            emit(MedicalResponseState.Success(response))
        } catch (e: Exception) {
            emit(MedicalResponseState.Error(e.message.toString()))
        }
    }


}