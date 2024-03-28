package com.example.myapplication.features.tareasDesc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.TaskViewModel

@Composable
fun TareasDescripcion2(

                       id:Long, // Pasar la tarea como argumento
   viewModel: TaskViewModel = hiltViewModel() // ViewModel para interactuar con la base de datos
    )
{
   // var editarTexto by remember { mutableStateOf(tarea.details) }



    var editarTexto by remember { mutableStateOf("") }

//editarTexto = viewModel.getTask(id)

    Column(modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 50.dp)) {
        OutlinedTextField(
            value = editarTexto,
           onValueChange = { editarTexto = it },
            label = { Text("Editar Texto") },

            modifier = Modifier.fillMaxWidth().weight(1f).padding(bottom = 16.dp)
        )

        // Botón para guardar el texto editado
        Button(
            onClick = {



                //tarea.details = editarTexto

              //  viewModel.updateTask(tarea)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")

        }

    }

}

