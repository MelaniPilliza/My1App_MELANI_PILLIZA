package com.example.my1app_melani_pilliza.presentation.ui.screens.login


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.my1app_melani_pilliza.presentation.navigation.Screen
import com.example.my1app_melani_pilliza.presentation.viewmodel.login.UsernamePasswordViewModel
import com.example.my1app_melani_pilliza.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    usernamePasswordViewModel: UsernamePasswordViewModel = koinViewModel()
) {
    val username by usernamePasswordViewModel.username.collectAsState()
    val password by usernamePasswordViewModel.password.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item{
                // IMAGEN
                Image(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(bottom = 16.dp)
                )
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
                    onClick = {
                        usernamePasswordViewModel.loginUser()
                        if (usernamePasswordViewModel.loginResult.value == true) {
                            navController.navigate(Screen.Products.route)
                        }
                    },
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
                    Spacer(modifier = Modifier.width(8.dp))
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

                //CREAR CUENTAS
                Button(
                    onClick = { navController.navigate(Screen.RegisterUser.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF1E88E5)
                    )
                ) {
                    Text("Crear una cuenta")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), viewModel())
}