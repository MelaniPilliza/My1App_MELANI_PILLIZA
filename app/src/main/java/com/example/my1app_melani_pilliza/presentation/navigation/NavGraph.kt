package com.example.my1app_melani_pilliza.presentation.navigation

import androidx.compose.runtime.Composable


import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my1app_melani_pilliza.presentation.ui.screens.login.LoginScreen
import com.example.my1app_melani_pilliza.presentation.ui.screens.product.AddProductScreen
import com.example.my1app_melani_pilliza.presentation.ui.screens.product.ProductScreen
import com.example.my1app_melani_pilliza.presentation.ui.screens.product.UpdateProductScreen



// El startDestination define la pantalla que se cargará cuando se abre la aplicación
@Composable
fun NavGraph(startDestination: String = Screen.Login.route) {
    // Cargamos el navController
    val navController = rememberNavController()
    // Creamos un NavHost que arranque con la pantalla de inicio
    NavHost(navController = navController, startDestination = startDestination) {
        // Definimos que para la ruta Screen.Home se cargue el composable HomeScreen(navController)
        composable(Screen.Login.route) {
            LoginScreen(
                navController
            )
        }
        // Definimos que para la ruta Screen.Details se cargue el composable DetailsScreen(navController, id)
        composable(Screen.Products.route) {

            ProductScreen(navController)
        }

        composable(Screen.AddProduct.route) {

            AddProductScreen(navController)
        }


        composable(Screen.UpdateProduct.route) { backStackEntry ->
            // Como esta ruta tiene parámetro puedo obtenerlo así, el nombre
            // de este parámetro debe coincidir con el declarado en Screen.Details.route
            val id = backStackEntry.arguments?.getString("id")
            // Crea la screen DetailsScreen con los parámetros
            UpdateProductScreen(navController, id)


        }
    }
}