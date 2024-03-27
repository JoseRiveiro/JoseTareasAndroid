package com.example.myapplication.features.tareasDesc

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val DESC_ROUTE = "TareasDescripcion"



fun NavHostController.navigateToTareasDescripcion(id: Long) {

//id : Long?
    this.navigate("$DESC_ROUTE/$id")

}
fun NavGraphBuilder.tareasDescripcionScreen() {
    composable(route = "$DESC_ROUTE/{id}",
        arguments = listOf(
            navArgument("id"){
                type = androidx.navigation.NavType.LongType
            }
        )

    ){
it.arguments?.getLong("id")?.let { id ->

    TareasDescripcion2(id)
}

    }

    }
