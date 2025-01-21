package com.example.appMelani.presentation.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appMelani.domain.model.Product
import com.example.appMelani.presentation.navigation.Screen
import com.example.appMelani.presentation.ui.components.ActionsMenu
import com.example.appMelani.presentation.viewmodel.products.ProductsViewModel

@Composable
fun ProductScreen(
    navController: NavController,
    productsViewModel: ProductsViewModel = viewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val products = productsViewModel.products.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ActionsMenu(navController = navController)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            Button(
                onClick = { navController.navigate(Screen.AddProduct.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF006D6D),
                    contentColor = Color.White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black
                ),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "+")
            }
        },
        content = { innerPadding ->

            LazyColumn(
                Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center
            ) {
                items(products) { product ->
                    key(product) {
                        ProductCard(product, productsViewModel)
                    }
                }
            }


        }

    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Título del Diálogo") },
            text = { Text("Este es un mensaje dentro del diálogo.") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun ProductCard(product: Product, productsViewModel: ProductsViewModel) {
    var expanded by remember { mutableStateOf(false) }
    Card(onClick = { expanded = !expanded }, modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "ID: ${product.id}")
            if (expanded) {
                Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Nombre: ${product.name}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Precio: ${product.price}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Descripcion: ${product.description}")
                    Spacer(modifier = Modifier.height(8.dp))
                    IconButton(onClick = { productsViewModel.removeProduct(product.id) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar Producto"
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun SnackbarExample() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Este es un Snackbar")
                    }
                }) {
                    Text("Mostrar Snackbar")
                }
            }
        }
    )
}

@Preview
@Composable
fun ProductsScreenPreview() {
    ProductScreen(navController = rememberNavController(), productsViewModel = viewModel())
}
