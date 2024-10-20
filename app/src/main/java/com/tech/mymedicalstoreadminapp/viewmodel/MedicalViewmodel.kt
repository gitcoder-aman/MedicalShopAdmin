package com.tech.mymedicalstoreadminapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.AddProductState
import com.tech.mymedicalstoreadminapp.responseState.GetAllUserState
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import com.tech.mymedicalstoreadminapp.responseState.UpdateUserState
import com.tech.mymedicalstoreadminapp.screen_state.ProductAddScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class MedicalViewmodel @Inject constructor(
    private val medicalRepository: MedicalRepository
) : ViewModel() {

    private val _getAllUserResponseState = MutableStateFlow(GetAllUserState())
    val getAllUserResponseState = _getAllUserResponseState.asStateFlow()

    private val _isApprovedUserResponseState = MutableStateFlow(UpdateUserState())
    val isApprovedUserResponseState = _isApprovedUserResponseState.asStateFlow()

    private val _addProductResponseState = MutableStateFlow(AddProductState())
    val addProductResponseState = _addProductResponseState.asStateFlow()

    private val _addProductScreenData = MutableStateFlow(ProductAddScreenState())
    val addProductScreenData = _addProductScreenData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductAddScreenState()
    )

    init {
        getAllUsers()
    }
    fun getAllUsers() {

        viewModelScope.launch {
            medicalRepository.getAllUsers().collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _getAllUserResponseState.value = GetAllUserState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _getAllUserResponseState.value = GetAllUserState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _getAllUserResponseState.value = GetAllUserState(error = it.message)
                    }
                }
            }
        }
    }

    fun doApprovedUser(userId: String, isApproved: Int){
        viewModelScope.launch {
            medicalRepository.doApprovedUser(
                userId = userId,
                isApproved = isApproved
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _isApprovedUserResponseState.value = UpdateUserState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _isApprovedUserResponseState.value = UpdateUserState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _isApprovedUserResponseState.value = UpdateUserState(error = it.message)
                    }
                }
            }
        }
    }
    fun addProduct(
        productName: String,
        productCategory: String,
        productPrice: Int,
        productStock: Int,
        productExpiryDate: String,
        productRating: Float,
        productDescription: String,
        productImageFile: MultipartBody.Part,
        productPower: String,
    ){

        viewModelScope.launch(Dispatchers.IO) {
            medicalRepository.addProduct(
                productName = productName.toRequestBody("text/plain".toMediaTypeOrNull()),
                productCategory = productCategory.toRequestBody("text/plain".toMediaTypeOrNull()),
                productPrice = productPrice.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                productStock = productStock.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                productExpiryDate = productExpiryDate.toRequestBody("text/plain".toMediaTypeOrNull()),
                productRating = productRating.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                productDescription = productDescription.toRequestBody("text/plain".toMediaTypeOrNull()),
                productImage = productImageFile,
                productPower = productPower.toRequestBody("text/plain".toMediaTypeOrNull())
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _addProductResponseState.value = AddProductState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _addProductResponseState.value = AddProductState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _addProductResponseState.value = AddProductState(error = it.message)
                    }
                }
            }
        }
    }
    fun resetProductAddScreenState(){
        _addProductScreenData.value = ProductAddScreenState(
            productName = mutableStateOf(""),
            productCategory = mutableStateOf(""),
            productPrice = mutableStateOf(""),
            productDescription = mutableStateOf(""),
            productPower = mutableStateOf(""),
            productRating = mutableStateOf(""),
            productStock = mutableStateOf(""),
            productExpiryDate = mutableStateOf(""),
            productImage = mutableStateOf(null)
        )
    }
}