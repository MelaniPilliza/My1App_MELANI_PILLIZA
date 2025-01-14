package com.example.appMelani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appMelani.presentation.navigation.NavGraph
import com.example.appMelani.presentation.ui.screens.login.LoginScreen
import com.example.appMelani.presentation.ui.screens.product.AddProductScreen
import com.example.appMelani.presentation.ui.screens.product.ProductScreen
import com.example.appMelani.presentation.ui.screens.product.SnackbarExample
import com.example.appMelani.presentation.ui.screens.tasks.TaskScreen
import com.example.appMelani.presentation.viewmodel.products.ProductsViewModel
import com.example.appMelani.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    private val fakeViewModel = ProductsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                NavGraph()
                //INICIAR SESIÓN
                    //LoginScreen(viewModel())
                //AÑADIR PRODUCTO
                    //AddProductScreen()
                //LISTA PRODUTOS
                    //ProductScreen(viewModel = fakeViewModel)
                //MOSTRAR MENU PRODUCTOS
                    //SnackbarExample()
            }
        }
    }
}