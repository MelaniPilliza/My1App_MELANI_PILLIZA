package appMelani.presentation.ui.screens.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import appMelani.presentation.viewmodel.login.UsernamePasswordViewModel


@Composable
fun LoginScreen(usernamePasswordViewModel: UsernamePasswordViewModel) {
    val username by usernamePasswordViewModel.username.collectAsState()
    val password by usernamePasswordViewModel.password.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TÍTULO
            Text(
                text = "INICIO DE SESIÓN",
                style = TextStyle(
                    color = Color(0xFF3C3C3C), // Gris oscuro para el título
                    fontSize = 26.sp, // Tamaño de fuente grande
                    fontWeight = FontWeight.SemiBold // Peso semi negrita
                ),
                modifier = Modifier
                    .padding(bottom = 32.dp)
            )

            // NOMBRE
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                value = username,
                onValueChange = {usernamePasswordViewModel.setUsername(it) },
                placeholder = { Text("Usuario") },
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(16.dp))

            // CONTRASEÑA
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                value = password,
                onValueChange = { usernamePasswordViewModel.setPassword(it)},
                placeholder = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp)

            )
            Spacer(Modifier.height(32.dp))

            // BOTÓN LOGIN
            Button(
                onClick = { /* Lógica para iniciar sesión */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Cuenta")
                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el ícono y el texto
                Text(text = "Iniciar sesión", color = Color.White)
            }

            Spacer(Modifier.height(16.dp))

            // BOTÓN "Cancelar"
            Button(
                onClick = { usernamePasswordViewModel.clear()},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancelar", color = Color.White)
            }
        }
    }
}