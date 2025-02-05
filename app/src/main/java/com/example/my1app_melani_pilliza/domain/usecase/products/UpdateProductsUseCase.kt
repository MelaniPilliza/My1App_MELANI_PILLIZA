package com.example.my1app_melani_pilliza.domain.usecase.products


import com.example.my1app_melani_pilliza.data.source.remote.ProductFirestoreRepository
import com.example.my1app_melani_pilliza.domain.model.Product

class UpdateProductsUseCase(
    private val productRepository: ProductFirestoreRepository
) {
    operator suspend fun invoke(product: Product): Unit {
        productRepository.update(product)
    }
}