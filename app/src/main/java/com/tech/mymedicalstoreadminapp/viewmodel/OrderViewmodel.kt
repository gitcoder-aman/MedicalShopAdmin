package com.tech.mymedicalstoreadminapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.GetAllOrderState
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import com.tech.mymedicalstoreadminapp.responseState.UpdateOrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewmodel @Inject constructor(
    private val medicalRepository: MedicalRepository
) : ViewModel() {

    private val _getAllOrders = MutableStateFlow(GetAllOrderState())
    val getAllOrders = _getAllOrders.asStateFlow()

    private val _doApprovedOrder = MutableStateFlow(UpdateOrderState())
    val doApprovedOrder = _doApprovedOrder.asStateFlow()


    init {
        getAllOrders()
    }

    fun getAllOrders() {
        viewModelScope.launch {
            medicalRepository.getAllOrders().collect {
                when (it) {
                    is MedicalResponseState.Loading -> {
                        _getAllOrders.value = GetAllOrderState(loading = true)
                    }

                    is MedicalResponseState.Success -> {
                        _getAllOrders.value = GetAllOrderState(data = it.data)
                    }

                    is MedicalResponseState.Error -> {
                        _getAllOrders.value = GetAllOrderState(error = it.message)
                    }
                }
            }
        }
    }
    fun updateOrderApproved(orderId : String , isApprovedOrder : Int){
        viewModelScope.launch {
            medicalRepository.doApprovedOrder(
                orderId = orderId,
                isApprovedOrder = isApprovedOrder
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _doApprovedOrder.value = UpdateOrderState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _doApprovedOrder.value = UpdateOrderState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _doApprovedOrder.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }
}