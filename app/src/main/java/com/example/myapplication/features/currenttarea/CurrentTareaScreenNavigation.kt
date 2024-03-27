package com.example.myapplication.features.currenttarea

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val CURRENT_TAREA_ROUTE = "current_tarea"



fun NavHostController.navigateToCurrentTareaScreen(id: Long) {
//id : Long?
    this.navigate("$CURRENT_TAREA_ROUTE/$id")

}
fun NavGraphBuilder.currentTareaScreen(

) {
    composable(route = "$CURRENT_TAREA_ROUTE/{id}"){
      //  CurrentTareaScreen()
    }

}
