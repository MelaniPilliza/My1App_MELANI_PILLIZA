package com.example.my1app_melani_pilliza.presentation.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.my1app_melani_pilliza.presentation.viewmodel.products.UpdateProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpdateProductScreen(
    navController: NavController,
    productId: String?,
    updateProductViewModel: UpdateProductViewModel = koinViewModel()
) {
    updateProductViewModel.setId(productId)
    val product by updateProductViewModel.product.collectAsState()
    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Text("Id: ${product.id}")
            Spacer(modifier = Modifier.height(16.dp))

            //NOMBRE
            TextField(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                value = product.name,
                onValueChange = {
                    updateProductViewModel.setName(it)
                },
                label = { Text("Nombre") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // PRECIO
            TextField(
                value = product.price.toString(),
                onValueChange = { newPrice ->
                    newPrice.toDoubleOrNull()?.let {
                        updateProductViewModel.setPrice(it)
                    }
                },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            )
            Spacer(Modifier.height(16.dp))

            // CANTIDAD
            TextField(
                value = product.stock.toString(),
                onValueChange = { newStock ->
                    newStock.toIntOrNull()?.let {
                        updateProductViewModel.setStock(it)
                    }
                },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            )
            Spacer(Modifier.height(16.dp))

            //DESCRIPCION
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                maxLines = 10,
                value = product.description,
                onValueChange = {
                    updateProductViewModel.setDescription(it)
                },
                label = { Text("Descripcion") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                updateProductViewModel.save()
                navController.popBackStack()
            }) {
                Text(text = "Actualizar")
            }
        }
    }
}