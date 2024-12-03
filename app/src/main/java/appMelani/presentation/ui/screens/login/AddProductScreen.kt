package appMelani.presentation.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AddProductScreen() {
    // Estado para los campos de texto
    val name = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    // Surface para el diseño de la pantalla
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
            // Título elegante con un estilo personalizado
            Text(
                text = "AÑADIR PRODUCTO",
                style = TextStyle(
                    color = Color(0xFF3C3C3C), // Gris oscuro para el título
                    fontSize = 26.sp, // Tamaño de fuente grande
                    fontWeight = FontWeight.SemiBold // Peso semi negrita
                ),
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Nombre del producto
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                placeholder = { Text(text = "Product Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )
            Spacer(Modifier.height(16.dp))

            // Precio del producto
            TextField(
                value = price.value,
                onValueChange = { price.value = it },
                placeholder = { Text(text = "Price") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )
            Spacer(Modifier.height(16.dp))

            // Descripción del producto
            TextField(
                value = description.value,
                onValueChange = { description.value = it },
                placeholder = { Text(text = "Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White),
                maxLines = 3
            )
            Spacer(Modifier.height(16.dp))

            // Botón de "Registrar Producto" sin funcionalidad
            Button(
                onClick = { /* Sin funcionalidad */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Register Product", color = Color.White)
            }

            Spacer(Modifier.height(16.dp))

            // Botón de "Cancelar" sin funcionalidad
            Button(
                onClick = { /* Sin funcionalidad */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Cancel", color = Color.White)
            }
        }
    }
}