package appMelani.presentation.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import appMelani.presentation.viewmodel.products.ProductViewModel


@Composable
fun AddProductScreen(navController: NavController,
                     productViewModel: ProductViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
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
                    .align(Alignment.CenterHorizontally)
            )

            // NOMBRE
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Nombre del Producto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            )
            Spacer(Modifier.height(16.dp))

            // PRECIO
            TextField(
                value = price,
                onValueChange = { newPrice ->
                    price = if (newPrice.isBlank()) "" else newPrice.toDoubleOrNull()?.toString() ?: price
                },
                placeholder = { Text("Precio") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            )
            Spacer(Modifier.height(16.dp))

            // DESCRIPCIÓN
            TextField(
                value = description,
                onValueChange = { description = it },
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
                onClick = {  },
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

            // BOTÓN "Cancelar"
            Button(
                onClick = { },
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
            }
        }
    }
}

@Preview
@Composable
fun AddProductScreenPreview() {
    AddProductScreen(navController = rememberNavController())
}