package com.example.myapplication.features.tareasDesc

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val DESC_ROUTE = "TareasDescripcion"



fun NavHostController.navigateToTareasDescripcion(){
    this.navigate(DESC_ROUTE)
}
fun NavGraphBuilder.TareasDescripcion(
    navHostController: NavHostController
) {

    composable(route = DESC_ROUTE) {

        }
    }
