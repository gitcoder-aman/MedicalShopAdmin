package com.tech.mymedicalstoreadminapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.AddProductState
import com.tech.mymedicalstoreadminapp.responseState.GetAllOrderState
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
        productImage: String,
        productPower: String,
    ){
        viewModelScope.launch(Dispatchers.IO) {
            medicalRepository.addProduct(
                productName = productName,
                productCategory = productCategory,
                productPrice = productPrice,
                productStock = productStock,
                productExpiryDate = productExpiryDate,
                productRating = productRating,
                productDescription = productDescription,
                productImage = productImage,
                productPower = productPower
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