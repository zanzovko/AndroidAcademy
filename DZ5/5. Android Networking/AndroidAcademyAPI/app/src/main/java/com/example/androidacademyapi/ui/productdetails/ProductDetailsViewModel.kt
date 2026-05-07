package com.example.androidacademyapi.ui.productdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidacademyapi.data.model.Product
import com.example.androidacademyapi.data.repository.ProductRepository
import kotlinx.coroutines.launch

sealed class ProductDetailsUIState {
    object Loading : ProductDetailsUIState()
    data class Success(val product: Product) : ProductDetailsUIState()
    data class Error(val message: String) : ProductDetailsUIState()
}

class ProductDetailsViewModel(
    private val repository: ProductRepository,
    private val productId: Int
) : ViewModel() {

    private val _productDetailsUIState = mutableStateOf<ProductDetailsUIState>(ProductDetailsUIState.Loading)
    val productDetailsUIState: State<ProductDetailsUIState> = _productDetailsUIState

    init {
        getProductById()
    }

    private fun getProductById() {
        viewModelScope.launch {
            _productDetailsUIState.value = ProductDetailsUIState.Loading
            repository.getProductById(productId)
                .onSuccess { product ->
                    _productDetailsUIState.value = ProductDetailsUIState.Success(product)
                }
                .onFailure { e ->
                    _productDetailsUIState.value = ProductDetailsUIState.Error(e.message ?: "Unknown error")
                }
        }
    }
}

class ProductDetailsViewModelFactory(
    private val repository: ProductRepository,
    private val productId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailsViewModel(repository, productId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}