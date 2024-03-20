package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.features.tareasDesc.TareasDescripcion
import com.example.myapplication.features.tareasDesc.navigateToTareasDescripcion
import com.example.myapplication.features.tareasScreen.TodoApp2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
Box(modifier = Modifier.padding(PaddingValues())){
    NavHost(navController = navHostController, startDestination = "TareasScreen" ){
      composable("TareasScreen") {
      TodoApp2(navigateToTareasDescripcion = {Tarea -> navHostController.navigateToTareasDescripcion()})
          

      }
        TareasDescripcion()
                
            }
        }


    }


}


        }







