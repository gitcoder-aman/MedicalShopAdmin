package com.tech.mymedicalstoreadminapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.GetAllUserState
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import com.tech.mymedicalstoreadminapp.responseState.UpdateUserState
import com.tech.mymedicalstoreadminapp.state.MessageResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewmodel @Inject constructor(
    private val medicalRepository: MedicalRepository
) : ViewModel() {

    private val _getAllUserResponseState = MutableStateFlow(GetAllUserState())
    val getAllUserResponseState = _getAllUserResponseState.asStateFlow()

    private val _isApprovedUserResponseState = MutableStateFlow(UpdateUserState())
    val isApprovedUserResponseState = _isApprovedUserResponseState.asStateFlow()

    private val _isBlockedUserResponseState = MutableStateFlow(UpdateUserState())
    val isBlockedUserResponseState = _isBlockedUserResponseState.asStateFlow()

    private val _isDeleteUserResponseState = MutableStateFlow(MessageResponseState())
    val isDeleteUserResponseState = _isDeleteUserResponseState.asStateFlow()

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
    fun updateBlockUser(userId: String, isBlocked: Int){
        viewModelScope.launch {
            medicalRepository.updateBlockStatus(
                userId = userId,
                isBlock = isBlocked
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _isBlockedUserResponseState.value = UpdateUserState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _isBlockedUserResponseState.value = UpdateUserState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _isBlockedUserResponseState.value = UpdateUserState(error = it.message)
                    }
                }
            }
        }
    }
    fun deleteUser(userId: String){
        viewModelScope.launch {
            medicalRepository.deleteUser(
                userId = userId
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _isDeleteUserResponseState.value = MessageResponseState(isLoading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _isDeleteUserResponseState.value = MessageResponseState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _isDeleteUserResponseState.value = MessageResponseState(error = it.message)
                    }

                }
            }
        }
    }
    fun resetUserState(){
        _isApprovedUserResponseState.value = UpdateUserState(
            loading = false,
            data = null,
            error = null
        )
        _isBlockedUserResponseState.value = UpdateUserState(
            loading = false,
            data = null,
            error = null
        )
        _isDeleteUserResponseState.value = MessageResponseState(
            isLoading = false,
            data = null,
            error = null
        )
    }
}