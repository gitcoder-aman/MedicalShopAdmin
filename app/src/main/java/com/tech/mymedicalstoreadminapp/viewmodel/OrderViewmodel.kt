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

    private val _getAllOrdersState = MutableStateFlow(GetAllOrderState())
    val getAllOrdersState = _getAllOrdersState.asStateFlow()

    private val _getSpecificOrderState = MutableStateFlow(GetAllOrderState())
    val getSpecificOrderState = _getSpecificOrderState.asStateFlow()

    private val _doApprovedOrder = MutableStateFlow(UpdateOrderState())
    val doApprovedOrder = _doApprovedOrder.asStateFlow()

    private val _updateShippedOrderState = MutableStateFlow(UpdateOrderState())
    val updateShippedOrderState = _updateShippedOrderState.asStateFlow()

    private val _updateOutOfDeliveryOrderState = MutableStateFlow(UpdateOrderState())
    val updateOutOfDeliveryOrderState = _updateOutOfDeliveryOrderState.asStateFlow()

    private val _updateDeliveredOrderState = MutableStateFlow(UpdateOrderState())
    val updateDeliveredOrderState = _updateDeliveredOrderState.asStateFlow()

    private val _updateCancelledOrderState = MutableStateFlow(UpdateOrderState())
    val updateCancelledOrderState = _updateCancelledOrderState.asStateFlow()


    init {
        getAllOrders()
    }

    fun getAllOrders() {
        viewModelScope.launch {
            medicalRepository.getAllOrders().collect {
                when (it) {
                    is MedicalResponseState.Loading -> {
                        _getAllOrdersState.value = GetAllOrderState(loading = true)
                    }

                    is MedicalResponseState.Success -> {
                        _getAllOrdersState.value = GetAllOrderState(data = it.data)
                    }

                    is MedicalResponseState.Error -> {
                        _getAllOrdersState.value = GetAllOrderState(error = it.message)
                    }
                }
            }
        }
    }

    fun updateOrderApproved(orderId : String , isApprovedOrder : Int, orderStatusStep : String){
        viewModelScope.launch {
            medicalRepository.doApprovedOrder(
                orderId = orderId,
                isApprovedOrder = isApprovedOrder,
                orderStatusStep = orderStatusStep
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
    fun updateShippedDateOrder(orderId : String , shippedDate : String,orderStatusStep : String){
        viewModelScope.launch {
            medicalRepository.updateShippedOrder(
                orderId = orderId,
                shippedDate = shippedDate,
                orderStatusStep = orderStatusStep
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _updateShippedOrderState.value = UpdateOrderState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _updateShippedOrderState.value = UpdateOrderState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _updateShippedOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }
    fun updateOutOfDeliveryDateOrder(orderId : String , outOfDeliveryDate : String,orderStatusStep : String){
        viewModelScope.launch {
            medicalRepository.updateOutOfDeliveryOrder(
                orderId = orderId,
                outOfDeliveryDate = outOfDeliveryDate,
                orderStatusStep = orderStatusStep
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _updateOutOfDeliveryOrderState.value = UpdateOrderState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _updateOutOfDeliveryOrderState.value = UpdateOrderState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _updateOutOfDeliveryOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }
    fun updateDeliveredDateOrder(orderId : String , deliveredDate : String,orderStatusStep : String){
        viewModelScope.launch {
            medicalRepository.updateDeliveredOrder(
                orderId = orderId,
                deliveredDate = deliveredDate,
                orderStatusStep = orderStatusStep
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _updateDeliveredOrderState.value = UpdateOrderState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _updateDeliveredOrderState.value = UpdateOrderState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _updateDeliveredOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }
    fun updateCancelledStatusOrder(orderId : String , cancelStatus : String,orderStatusStep : String){
        viewModelScope.launch {
            medicalRepository.updateCancelOrder(
                orderId = orderId,
                cancelStatus = cancelStatus,
                orderStatusStep = orderStatusStep
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _updateCancelledOrderState.value = UpdateOrderState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _updateCancelledOrderState.value = UpdateOrderState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _updateCancelledOrderState.value = UpdateOrderState(error = it.message)
                    }
                }
            }
        }
    }

    fun getSpecificOrder(orderId : String){
        viewModelScope.launch {
            medicalRepository.getSpecificOrder(orderId).collect {
                when (it) {
                    is MedicalResponseState.Loading -> {
                        _getSpecificOrderState.value = GetAllOrderState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _getSpecificOrderState.value = GetAllOrderState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _getSpecificOrderState.value = GetAllOrderState(error = it.message)
                    }
                }
            }
        }
    }
    fun resetUpdateOrderState(){
        _doApprovedOrder.value = UpdateOrderState()
        _updateShippedOrderState.value = UpdateOrderState()
        _updateOutOfDeliveryOrderState.value = UpdateOrderState()
        _updateDeliveredOrderState.value = UpdateOrderState()
    }
}