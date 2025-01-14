package com.example.appMelani.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import com.example.appMelani.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(
        listOf(
            Product(1, "Leggins", 25.99, "Leggins deportivos de alta calidad y cómodo ajuste"),
            Product(2, "Top", 19.99, "Top sin mangas ideal para hacer ejercicio o uso casual"),
            Product(3, "Jogger", 39.99, "Pantalones jogger cómodos y con estilo para uso diario"),
            Product(4, "Camiseta", 15.99, "Camiseta de algodón suave y respirable para todo el día")
        )
    )

    val products: StateFlow<List<Product>> = _products

    fun removeProduct(id: Int) {
        _products.value = _products.value.filter { it.id != id }
    }
}