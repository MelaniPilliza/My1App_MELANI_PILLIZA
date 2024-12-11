package appMelani.presentation.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("login")
    data object Products: Screen("products")
    data object AddProduct: Screen("products/add")
    data object Tasks: Screen("tasks")
}