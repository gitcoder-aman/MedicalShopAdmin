package com.tech.mymedicalstoreadminapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.GetAllSellHistoryState
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import com.tech.mymedicalstoreadminapp.state.MessageResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellHistoryViewmodel @Inject constructor(
    private val medicalRepository: MedicalRepository
) : ViewModel(){

    private val _addInSellHistoryState = MutableStateFlow(MessageResponseState())
    val addInSellHistoryState = _addInSellHistoryState.asStateFlow()

    private val _getAllSellHistoryState = MutableStateFlow(GetAllSellHistoryState())
    val getAllSellHistoryState = _getAllSellHistoryState.asStateFlow()

    fun addInSellHistory(
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
    ){
        viewModelScope.launch {
            medicalRepository.addInSellHistory(
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
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _addInSellHistoryState.value = MessageResponseState(isLoading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _addInSellHistoryState.value = MessageResponseState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _addInSellHistoryState.value = MessageResponseState(error = it.message)
                    }
                }
            }
        }
    }

    fun getAllSellHistory(){
        viewModelScope.launch {
            medicalRepository.getAllSellHistory().collect {
                when (it) {
                    is MedicalResponseState.Loading -> {
                        _getAllSellHistoryState.value = GetAllSellHistoryState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _getAllSellHistoryState.value = GetAllSellHistoryState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _getAllSellHistoryState.value = GetAllSellHistoryState(error = it.message)
                    }
                }
            }
        }
    }
    fun resetAddInSellHistoryState(){
        _addInSellHistoryState.value = MessageResponseState()
    }
}