package com.example.my1app_melani_pilliza.presentation.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.my1app_melani_pilliza.presentation.viewmodel.products.AddProductViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddProductScreen(
    navController: NavController,
    addProductViewModel: AddProductViewModel = koinViewModel()
) {
    val product by addProductViewModel.product.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
             item {
                //TITULO
                Text(
                    text = "AÑADIR PRODUCTO",
                    style = TextStyle(
                        color = Color(0xFF3C3C3C),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                )

                // NOMBRE
                TextField(
                    value = product.name,
                    maxLines = 1,
                    onValueChange = {
                        addProductViewModel.setName(it)
                    },
                    placeholder = { Text("Nombre del Producto") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                )
                Spacer(Modifier.height(16.dp))

                // PRECIO
                TextField(
                    value = product.price.toString(),
                    onValueChange = { newPrice ->
                        newPrice.toDoubleOrNull()?.let {
                            addProductViewModel.setPrice(it)
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
                             addProductViewModel.setStock(it)
                         }
                     },
                     label = { Text("Cantidad") },
                     keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                     modifier = Modifier
                         .fillMaxWidth()
                         .background(Color.White),
                 )
                 Spacer(Modifier.height(16.dp))

                // DESCRIPCIÓN
                TextField(
                    value = product.description,
                    onValueChange = {
                        addProductViewModel.setDescription(it)
                    },
                    placeholder = { Text("Descripción") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.White),
                    maxLines = 3,
                )
                Spacer(Modifier.height(16.dp))

                // BOTÓN "Registrar Producto"
                Button(
                    onClick = {
                        addProductViewModel.save()
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Black
                    )
                ) {
                    Text(text = "Registrar Producto")
                }

                Spacer(Modifier.height(16.dp))
                /**
                // BOTÓN "Cancelar"
                Button(
                    onClick = {
                        name = ""
                        price = ""
                        description = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White,
                        disabledContentColor = Color.DarkGray,
                        disabledContainerColor = Color.LightGray
                    )
                ) {
                    Text(text = "Cancelar")
                }*/
            }
        }
    }
}

@Preview
@Composable
fun AddProductScreenPreview() {
    AddProductScreen(navController = rememberNavController())
}