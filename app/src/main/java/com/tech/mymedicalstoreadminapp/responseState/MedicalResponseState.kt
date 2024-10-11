package com.tech.mymedicalstoreadminapp.responseState


//this is main state of app
sealed class MedicalResponseState<out T> {
    data object Loading : MedicalResponseState<Nothing>()
    data class Success<out T>(val data: T) : MedicalResponseState<T>()
    data class Error(val message: String) : MedicalResponseState<Nothing>()
}