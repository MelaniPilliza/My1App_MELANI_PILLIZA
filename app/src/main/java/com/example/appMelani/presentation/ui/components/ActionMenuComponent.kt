package com.example.appMelani.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionsMenu(navController: NavController) {
    // Estado para controlar la visibilidad del menú
    var expanded by remember { mutableStateOf(false) }

    // Barra de herramientas (TopAppBar)
    TopAppBar(
        title = { Text("Menú de Inventario", color = Color(0xFF006D6D)) },
        actions = {
            // Botón de menú
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Menú"
                )
            }

            // Menú desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text("Home")
                    },
                    onClick = {
                        // Acción 2
                        expanded = false
                        navController.popBackStack()
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text("Back")
                    },
                    onClick = {
                        // Acción 2
                        expanded = false
                        navController.popBackStack()
                    })
                // Línea divisoria entre elementos
                HorizontalDivider()
                DropdownMenuItem(
                    text = { Text("Other action") },
                    onClick = {
                        // Simplemente cierra el menú desplegable
                        expanded = false
                    })
            }
        }

    )
}

@Preview
@Composable
fun ActionsMenuPreview() {
    ActionsMenu(rememberNavController())
}