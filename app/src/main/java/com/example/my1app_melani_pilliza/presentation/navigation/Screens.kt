package com.example.my1app_melani_pilliza.presentation.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Products : Screen("products")
    data object AddProduct : Screen("products/add")
    data object UpdateProduct : Screen("products/update/{id}") {
        fun createRoute(id: String): String = "products/update/$id"
    }
    object RegisterUser : Screen("registerUser")
}