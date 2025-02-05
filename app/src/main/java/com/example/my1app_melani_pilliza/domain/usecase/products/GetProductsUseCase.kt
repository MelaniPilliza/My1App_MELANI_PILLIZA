package com.example.my1app_melani_pilliza.domain.usecase.products

import com.example.my1app_melani_pilliza.data.source.remote.ProductFirestoreRepository
import com.example.my1app_melani_pilliza.domain.model.Product
import kotlinx.coroutines.flow.Flow

class GetProductUseCase(
    private val productRepository: ProductFirestoreRepository
) {
    suspend operator fun invoke(productId: String?): Product? {
        return productRepository.getProductById(productId)
    }
}