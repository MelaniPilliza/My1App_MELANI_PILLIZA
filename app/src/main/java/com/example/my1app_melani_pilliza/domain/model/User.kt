package com.example.my1app_melani_pilliza.domain.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class User(
    @DocumentId val id: String = "",
    @PropertyName("user_name") val name: String = "",
    val email: String = "",
    val age: Int = 0,
    @ServerTimestamp val createdAt: Date? = null
)