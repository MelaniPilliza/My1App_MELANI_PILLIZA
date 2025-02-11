package com.example.my1app_melani_pilliza.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my1app_melani_pilliza.domain.model.Product
import com.example.my1app_melani_pilliza.domain.usecase.products.AddProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddProductViewModel(
    val addProductUseCase: AddProductsUseCase
) : ViewModel() {
    private val _product = MutableStateFlow(
        Product("", "", 0.0,0,"")
    )
    val product: StateFlow<Product> = _product

    fun setId(id: String) {
        _product.value = _product.value.copy(
            id = id
        )
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
            addProductUseCase(product.value)
        }
    }
}