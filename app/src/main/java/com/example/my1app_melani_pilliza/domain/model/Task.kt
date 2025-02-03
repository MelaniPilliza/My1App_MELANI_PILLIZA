package com.example.my1app_melani_pilliza.domain.model

data class Task(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false)

