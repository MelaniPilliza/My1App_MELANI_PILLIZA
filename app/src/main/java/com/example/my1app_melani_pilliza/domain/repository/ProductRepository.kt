package com.example.my1app_melani_pilliza.domain.repository

import com.example.my1app_melani_pilliza.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun list(): Flow<List<Product>>
    suspend fun save(product: Product)
    suspend fun delete(id: String)
}