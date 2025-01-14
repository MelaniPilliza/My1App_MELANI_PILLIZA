package com.example.appMelani.domain.model

data class Task(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false)

