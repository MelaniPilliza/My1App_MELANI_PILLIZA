package appMelani.presentation.navigation

import androidx.compose.runtime.Composable


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import appMelani.presentation.ui.screens.login.LoginScreen

/*
// StartDestination: define la pantalla que se cargará cuando se abre la aplicación
@Composable
fun NavGraph(startDestination: String = Screen.Login.route) {
    val navController = rememberNavController()
    //NavHost para que arranque con la pantalla de inicio
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LoginScreen(

            )
        }

    }
}
*/