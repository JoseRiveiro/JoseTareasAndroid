package com.example.myapplication.features.tareasScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.myapplication.features.tareasDesc.navigateToTareasDescripcion

const val TareasScreen_ROUTE = "TareasScreenRoute"


fun NavGraphBuilder.todo2(
    navHostController: NavHostController
) {

    composable(route = TareasScreen_ROUTE) {
        TodoApp2(navigateToTareasDescripcion = { Tarea -> navHostController.navigateToTareasDescripcion() })


    }
}