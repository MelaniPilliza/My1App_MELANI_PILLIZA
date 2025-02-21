package com.example.my1app_melani_pilliza.presentation.ui.screens.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.my1app_melani_pilliza.domain.model.Product
import com.example.my1app_melani_pilliza.presentation.navigation.Screen
import com.example.my1app_melani_pilliza.presentation.ui.components.ActionsMenu
import com.example.my1app_melani_pilliza.presentation.viewmodel.products.ProductsScreenViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductScreen(
    navController: NavController,
    productsScreenViewModel: ProductsScreenViewModel = koinViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val products = productsScreenViewModel.products.collectAsState().value
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
                items(products, key = { it.id }) { product ->
                    ProductCard(product, productsScreenViewModel, navController)
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
fun ProductCard(product: Product, productsScreenViewModel: ProductsScreenViewModel, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp), // Bordes redondeados
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), // Sombra
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = product.name.uppercase(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006D6D)
            )

            Text(
                text = "ID: ${product.id}",
                fontSize = 12.sp,
                color = Color.Gray
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))

                Column {
                    Text(text = "💰 Precio: ${product.price} €", fontSize = 16.sp)
                    Text(text = "📦 Stock: ${product.stock} unidades", fontSize = 16.sp)
                    Text(text = "📝 Descripción: ${product.description}", fontSize = 14.sp, color = Color.DarkGray)

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { productsScreenViewModel.removeProduct(product.id) },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar Producto",
                                tint = Color.Red
                            )
                        }

                        IconButton(
                            onClick = { navController.navigate(Screen.UpdateProduct.createRoute(product.id)) },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar Producto",
                                tint = Color(0xFF6200EE)
                            )
                        }
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
    ProductScreen(navController = rememberNavController(), productsScreenViewModel = viewModel())
}
