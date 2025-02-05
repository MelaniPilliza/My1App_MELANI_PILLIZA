package com.example.my1app_melani_pilliza.domain.usecase.products

import com.example.my1app_melani_pilliza.data.source.remote.ProductFirestoreRepository

class DeleteProductsUseCase(
    private val productRepository: ProductFirestoreRepository
) {
    operator suspend fun invoke(id: String): Unit {
        productRepository.delete(id)
    }
}