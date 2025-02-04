package com.example.my1app_melani_pilliza.domain.usecase.products

import com.example.my1app_melani_pilliza.data.source.remote.ProductFirestoreRepository
import com.example.my1app_melani_pilliza.domain.model.Product
import kotlinx.coroutines.flow.Flow

class ListProductsUseCase(
    private val productRepository: ProductFirestoreRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.list()
    }
}