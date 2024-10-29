package com.tech.mymedicalstoreadminapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.AddStockUserState
import com.tech.mymedicalstoreadminapp.responseState.GetAllStockUserState
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserStockViewmodel @Inject constructor(
    private val medicalRepository: MedicalRepository
) : ViewModel() {

    private val _addUserStockState = MutableStateFlow(AddStockUserState())
    val addUserStockState = _addUserStockState.asStateFlow()

    private val _getAllUserStockState = MutableStateFlow(GetAllStockUserState())
    val getAllUserStockState = _getAllUserStockState.asStateFlow()

    fun addOrderInUserStock(
        userId : String,
        orderId : String,
        productId : String,
        productName : String,
        userName : String,
        certified : Boolean,
        productStock : Int,
        productPrice : Int,
        productCategory : String
    ){
       viewModelScope
           .launch {
               medicalRepository.addOrderInUserStock(
                   userId = userId,
                   orderId = orderId,
                   productId = productId,
                   productName = productName,
                   userName = userName,
                   certified = certified,
                   productStock = productStock,
                   productPrice = productPrice,
                   productCategory = productCategory
               ).collect{
                   when(it){
                       is MedicalResponseState.Loading -> {
                           _addUserStockState.value = AddStockUserState(loading = true)
                       }
                       is MedicalResponseState.Success -> {
                           _addUserStockState.value = AddStockUserState(data = it.data)
                       }
                       is MedicalResponseState.Error -> {
                           _addUserStockState.value = AddStockUserState(error = it.message)
                       }
                   }
               }
           }
    }

    fun getAllUserStock(){
        viewModelScope.launch {
            medicalRepository.getAllUserStock().collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _getAllUserStockState.value = GetAllStockUserState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _getAllUserStockState.value = GetAllStockUserState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _getAllUserStockState.value = GetAllStockUserState(error = it.message)
                    }
                }
            }
        }
    }
    fun resetAddStockUserState(){
        _addUserStockState.value = AddStockUserState()
    }
}