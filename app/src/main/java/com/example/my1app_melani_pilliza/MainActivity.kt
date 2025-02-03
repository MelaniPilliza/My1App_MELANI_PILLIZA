package com.example.my1app_melani_pilliza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.my1app_melani_pilliza.presentation.navigation.NavGraph
import com.example.my1app_melani_pilliza.presentation.viewmodel.products.ProductsViewModel
import com.example.my1app_melani_pilliza.ui.theme.AppTheme


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