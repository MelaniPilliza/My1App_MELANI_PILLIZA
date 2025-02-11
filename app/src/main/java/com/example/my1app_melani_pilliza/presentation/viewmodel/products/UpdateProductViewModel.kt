package com.example.my1app_melani_pilliza.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my1app_melani_pilliza.domain.model.Product
import com.example.my1app_melani_pilliza.domain.usecase.products.AddProductsUseCase
import com.example.my1app_melani_pilliza.domain.usecase.products.GetProductUseCase
import com.example.my1app_melani_pilliza.domain.usecase.products.UpdateProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpdateProductViewModel(
    val updateProductUseCase: UpdateProductsUseCase,
    val getProductUseCase: GetProductUseCase
) : ViewModel() {
    private val _product = MutableStateFlow(
        Product("", "",0.0,0, "")
    )
    val product: StateFlow<Product> = _product

    fun setId(id: String?) {
        viewModelScope.launch {
            _product.value = getProductUseCase(id) ?:  Product("", "",0.0,0,"")
        }
    }

    fun setName(name: String) {
        _product.value = _product.value.copy(
            name = name
        )
    }

    fun setPrice(price: Double) {
        _product.value = _product.value.copy(
            price= price
        )
    }

    fun setStock(stock: Int) {
        _product.value = _product.value.copy(
            stock= stock
        )
    }

    fun setDescription(description: String) {
        _product.value = _product.value.copy(
            description = description
        )
    }

    fun save() {
        viewModelScope.launch {
            updateProductUseCase(product.value)
        }
    }
}