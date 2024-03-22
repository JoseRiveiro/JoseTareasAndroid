package com.example.myapplication.features.tareasScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val TareasScreen_ROUTE = "TareasScreenRoute"



fun NavHostController.navigateToTareas(){
    this.navigate(TareasScreen_ROUTE)
}
fun NavGraphBuilder.todoScreen(
    navHostController: NavHostController
) {

    composable(route = TareasScreen_ROUTE) {
        TodoApp2(navigateToTareasDescripcion = { tarea -> navHostController.navigateToTareas() })
    }
}