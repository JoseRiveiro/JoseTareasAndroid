package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.features.tareasDesc.TareasDescripcion2
import com.example.myapplication.features.tareasDesc.navigateToTareasDescripcion
import com.example.myapplication.features.tareasScreen.TodoApp2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
Box(modifier = Modifier.padding(PaddingValues())){

    NavHost(navController = navHostController, startDestination = "TareasScreen" ){
      composable("TareasScreen") {
      TodoApp2(navigateToTareasDescripcion = {Tarea -> navHostController.navigateToTareasDescripcion()})
          

      }
        composable("TareasDesciprion") {
            TareasDescripcion2()
        }

                
            }
        }


            TopAppBar(
                title = { Text("Tareas") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go Back")
                    }
                },
                modifier = Modifier)

        }
    }


}









