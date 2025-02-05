package com.example.my1app_melani_pilliza.domain.model

import com.google.firebase.firestore.DocumentId

data class Product(
    @DocumentId var id: String = "",
    val name: String,
    val price: Double,
    val description: String
) {
    constructor() : this("", "",0.0, "")
}