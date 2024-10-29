package com.tech.mymedicalstoreadminapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import com.tech.mymedicalstoreadminapp.responseState.AddUpdateProductState
import com.tech.mymedicalstoreadminapp.responseState.MedicalResponseState
import com.tech.mymedicalstoreadminapp.state.MedicalProductResponseState
import com.tech.mymedicalstoreadminapp.state.MessageResponseState
import com.tech.mymedicalstoreadminapp.state.screen_state.ProductAddUpdateScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProductViewmodel @Inject constructor(
    private val medicalRepository: MedicalRepository
) : ViewModel() {

    private val _addProductResponseState = MutableStateFlow(AddUpdateProductState())
    val addProductResponseState = _addProductResponseState.asStateFlow()

    private val _addProductScreenData = MutableStateFlow(ProductAddUpdateScreenState())
    val addProductScreenData = _addProductScreenData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductAddUpdateScreenState()
    )
    private val _updateProductResponseData = MutableStateFlow(AddUpdateProductState())
    val updateProductResponseData = _updateProductResponseData.asStateFlow()

    private val _updateProductScreenData = MutableStateFlow(ProductAddUpdateScreenState())
    val updateProductScreenData = _updateProductScreenData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductAddUpdateScreenState()
    )
    private val _getAllProducts = MutableStateFlow(MedicalProductResponseState())
    val getAllProducts = _getAllProducts.asStateFlow()

    private val _getSpecificProduct = MutableStateFlow(MedicalProductResponseState())
    val getSpecificProduct = _getSpecificProduct.asStateFlow()

    private val _deleteProductState = MutableStateFlow(MessageResponseState())
    val deleteProductState = _deleteProductState.asStateFlow()

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
                        _addProductResponseState.value = AddUpdateProductState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _addProductResponseState.value = AddUpdateProductState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _addProductResponseState.value = AddUpdateProductState(error = it.message)
                    }
                }
            }
        }
    }
     fun getAllProducts(){
        viewModelScope.launch {
            medicalRepository.getAllProducts().collect{
                when(it){
                    is MedicalResponseState.Loading->{
                        _getAllProducts.value = MedicalProductResponseState(isLoading = true)
                    }
                    is MedicalResponseState.Success->{
                        _getAllProducts.value = MedicalProductResponseState(data = it.data.body())
                    }
                    is MedicalResponseState.Error->{
                        _getAllProducts.value = MedicalProductResponseState(error = it.message)
                    }
                }
            }
        }
    }
    fun getProductById(productId: String) {
        viewModelScope.launch {
            medicalRepository.getSpecificProduct(productId).collect{
                when(it){
                    is MedicalResponseState.Loading->{
                        _getSpecificProduct.value = MedicalProductResponseState(isLoading = true)
                    }
                    is MedicalResponseState.Success->{
                        _getSpecificProduct.value = MedicalProductResponseState(data = it.data.body())
                    }
                    is MedicalResponseState.Error->{
                        _getSpecificProduct.value = MedicalProductResponseState(error = it.message)
                    }
                }
            }
        }
    }
    fun updateProduct(
        productId: String,
        productImageFile: MultipartBody.Part
    ){
        viewModelScope.launch(Dispatchers.IO) {
            medicalRepository.updateProduct(
                productId = productId.toRequestBody("text/plain".toMediaTypeOrNull()),
                productName = updateProductScreenData.value.productName.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                productCategory = updateProductScreenData.value.productCategory.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                productPrice = updateProductScreenData.value.productPrice.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                productStock = updateProductScreenData.value.productStock.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                productExpiryDate = updateProductScreenData.value.productExpiryDate.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                productRating = updateProductScreenData.value.productRating.value.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                productDescription = updateProductScreenData.value.productDescription.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                productPower = updateProductScreenData.value.productPower.value.toRequestBody("text/plain".toMediaTypeOrNull()),
                productImage = productImageFile
            ).collect{
                when(it){
                    is MedicalResponseState.Loading -> {
                        _updateProductResponseData.value = AddUpdateProductState(loading = true)
                    }
                    is MedicalResponseState.Success -> {
                        _updateProductResponseData.value = AddUpdateProductState(data = it.data)
                    }
                    is MedicalResponseState.Error -> {
                        _updateProductResponseData.value = AddUpdateProductState(error = it.message)
                    }
                }
            }
        }
    }
    fun updateStockProductById(
        productId: String,
        productStock: Int
    ){
        viewModelScope.launch {
            medicalRepository.updateStockProduct(
                productId = productId,
                productStock = productStock
            ).collect{
                when(it){
                    is MedicalResponseState.Loading->{
                        _updateProductResponseData.value = AddUpdateProductState(loading = true)
                    }
                    is MedicalResponseState.Success-> {
                        _updateProductResponseData.value = AddUpdateProductState(data = it.data)
                    }
                    is MedicalResponseState.Error->{
                        _updateProductResponseData.value = AddUpdateProductState(error = it.message)
                    }
                }
            }
        }
    }
    fun deleteProduct(productId: String){
        viewModelScope.launch {
            medicalRepository.deleteProduct(productId).collect{
                when(it){
                    is MedicalResponseState.Loading->{
                        _deleteProductState.value = MessageResponseState(isLoading = true)
                    }
                    is MedicalResponseState.Success->{
                        _deleteProductState.value = MessageResponseState(data = it.data)
                    }
                    is MedicalResponseState.Error->{
                        _deleteProductState.value = MessageResponseState(error = it.message)
                    }
                }
            }
        }
    }
    fun resetProductAddScreenState(){
        _addProductScreenData.value = ProductAddUpdateScreenState(
            productId = mutableStateOf(""),
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
    fun resetProductUpdateScreenState(){
        _updateProductScreenData.value = ProductAddUpdateScreenState(
            productId = mutableStateOf(""),
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
    fun resetDeleteProductState(){
        _deleteProductState.value = MessageResponseState(
            isLoading = false,
            data = null,
            error = null
        )
    }


}