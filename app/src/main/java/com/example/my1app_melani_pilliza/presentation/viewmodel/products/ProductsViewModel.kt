package com.example.my1app_melani_pilliza.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import com.example.my1app_melani_pilliza.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(
        listOf(
            Product("", "Leggins", 25.99, "Leggins deportivos de alta calidad y cómodo ajuste"),
            Product("", "Top", 19.99, "Top sin mangas ideal para hacer ejercicio o uso casual"),
            Product("", "Jogger", 39.99, "Pantalones jogger cómodos y con estilo para uso diario"),
            Product("", "Camiseta", 15.99, "Camiseta de algodón suave y respirable para todo el día")
        )
    )

    val products: StateFlow<List<Product>> = _products

    fun removeProduct(id: String) {
        _products.value = _products.value.filter { it.id != id }
    }
}