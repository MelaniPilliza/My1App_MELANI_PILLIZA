package appMelani.presentation.ui.screens.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import appMelani.domain.model.Product
import appMelani.presentation.viewmodel.products.ProductsViewModel

@Composable
fun ProductScreen(viewModel: ProductsViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val products = viewModel.products.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            LazyColumn(
                Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center
            ) {
                items(products) { product ->
                    ProductCard(product, viewModel)
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
fun ProductCard(product: Product, viewModel: ProductsViewModel) {
    Card(
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID: ${product.id}", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Nombre: ${product.name}", modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(onClick = { viewModel.removeProduct(product.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar Producto"
                )
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


