package com.example.myapplication.features.tareasDesc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TareasDescripcion2(

    ) {
    var editarTexto by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = editarTexto,
            onValueChange = { editarTexto = it },
            label = { Text("Editar Texto") },

            modifier = Modifier.fillMaxWidth()
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        /*
            IconButton(
                onClick = { navController.popBackStack() }, // Llama a la función de devolución de llamada cuando se hace clic en el botón
                modifier = Modifier.padding(end = 8.dp) // Agrega un pequeño espacio entre el botón y el texto
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )

                Spacer(modifier = Modifier.weight(1f))
            }

         */
    }
    
}