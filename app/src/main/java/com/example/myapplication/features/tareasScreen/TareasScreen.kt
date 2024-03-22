package com.example.myapplication.features.tareasScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.Tarea
import com.example.myapplication.TaskViewModel

@Composable
fun TodoList(
    todos: List<Tarea>,
    onTaskCheckedChange: (Tarea) -> Unit,
    onTaskEdit: (Tarea) -> Unit,
    onDeleteTask :(Tarea) -> Unit,
    navigateToTareasDescripcion: (Tarea)-> Unit
) {
    LazyColumn {

        items(todos) { task ->

            TodoItem(task, onTaskCheckedChange, onTaskEdit, onDeleteTask, navigateToTareasDescripcion)

     }
    }
}

@Composable
fun TodoItem(todo: Tarea,
             onTaskCheckedChange: (Tarea) -> Unit,
             onTaskEdit: (Tarea) -> Unit,
             onDeleteTask: (Tarea)-> Unit,
             navigateToTareasDescripcion: (Tarea)-> Unit
            ) {
    Box(modifier = Modifier.clickable { navigateToTareasDescripcion(todo)}){
    Card(
        modifier = Modifier

            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier

                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            var checked by remember { mutableStateOf(todo.isCompleted) }
            Checkbox(

                checked = checked,

                onCheckedChange = {
                    checked = it

                    onTaskCheckedChange(todo.copy(isCompleted = it))
                },
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyMedium,
                color = if (todo.isCompleted) Color.Gray else Color.Unspecified
            )
            Spacer(modifier = Modifier.weight(1f))

            // Empieza el botón para ir a la descripción de la tarea
/*
            IconButton(
                onClick = {
                    navigateToTareasDescripcion(todo)
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Task")
            }
            // Termina el botón



 */

            IconButton(
                onClick = {
                    onDeleteTask(todo)

                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.error)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}
}

@Composable
//Agregar HiltViewModewl
fun TodoApp2(modifier: Modifier = Modifier,
             viewModel: TaskViewModel = hiltViewModel(),
             navigateToTareasDescripcion: (Tarea)-> Unit
) {
    val todos by viewModel.state.collectAsState()
    var newTaskText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Lista de tareas
        TodoList(todos = todos, viewModel::updateTask, viewModel::addTask, viewModel::deleteTask,  navigateToTareasDescripcion )




        // Espaciador
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada de nueva tarea
        OutlinedTextField(
            value = newTaskText,
            onValueChange = { newTaskText = it },
            label = { Text("Nueva tarea") },

            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )
        // Botón agregar tarea
        IconButton(
            onClick = {
                if (newTaskText.isNotEmpty()) {
                    viewModel.addTask(title = newTaskText)
                    newTaskText = ""
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
        }
    }
}


