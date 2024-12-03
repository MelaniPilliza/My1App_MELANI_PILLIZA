package appMelani.presentation.ui.screens.login

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


@Composable
fun AddProductScreen() {
    // Estado para los campos de texto
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "Nombre del Producto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            )
            Spacer(Modifier.height(16.dp))

            // Precio del producto (campo numérico)
            TextField(
                value = price,
                onValueChange = { newPrice ->
                    // Validamos que el valor sea numérico
                    price = if (newPrice.isBlank()) "" else newPrice.toDoubleOrNull()?.toString() ?: price
                },
                placeholder = { Text(text = "Precio") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            )
            Spacer(Modifier.height(16.dp))

            // Descripción del producto
            TextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text(text = "Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White),
                maxLines = 3,
            )
            Spacer(Modifier.height(16.dp))

            // Botón de "Registrar Producto"
            Button(
                onClick = { /* Funcionalidad para registrar el producto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFF6200EE), // Color de fondo personalizado
                    contentColor = Color.White, // Color del texto
                    disabledContentColor = Color.Gray, // Color del texto cuando está deshabilitado
                    disabledContainerColor = Color.Black // Color de fondo cuando está deshabilitado
                )
            ) {
                Text(text = "Registrar Producto")
            }

            Spacer(Modifier.height(16.dp))

            // Botón de "Cancelar"
            Button(
                onClick = { /* Funcionalidad para cancelar la acción */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    containerColor = Color.Gray, // Color de fondo para el botón de cancelar
                    contentColor = Color.White, // Color del texto
                    disabledContentColor = Color.DarkGray, // Color del texto cuando está deshabilitado
                    disabledContainerColor = Color.LightGray // Color de fondo cuando está deshabilitado
                )
            ) {
                Text(text = "Cancelar")
            }
        }
    }
}