package com.example.my1app_melani_pilliza.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my1app_melani_pilliza.domain.model.Product
import com.example.my1app_melani_pilliza.domain.usecase.products.DeleteProductsUseCase
import com.example.my1app_melani_pilliza.domain.usecase.products.ListProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductsScreenViewModel(
    val listproductsUseCase: ListProductsUseCase,
    val deleteProductUseCase: DeleteProductsUseCase
) : ViewModel() {

    private val _products = listproductsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val products: StateFlow<List<Product>> = _products


    fun removeProduct(id: String) {
        viewModelScope.launch {
            deleteProductUseCase(id)
        }
    }
}