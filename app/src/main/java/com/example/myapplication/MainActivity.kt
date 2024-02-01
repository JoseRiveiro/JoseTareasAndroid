package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class TodoViewModel : ViewModel() {
    val todos = mutableStateListOf<Todo>()
}

data class Todo(

    val id: Int,
    val task: String,

    var isCompleted: Boolean = false

)
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                TodoApp(viewModel)
            }
        }
    }

@Composable
fun TodoApp(viewModel: TodoViewModel) {
    val todos = viewModel.todos
    var newTaskText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Lista de tareas
        TodoList(todos = todos, onTaskCheckedChange = { todo ->
            viewModel.todos[viewModel.todos.indexOf(todo)] = todo.copy(isCompleted = !todo.isCompleted)
        }, onTaskDelete = { todo ->
            viewModel.todos.remove(todo)
        })

        // Espaciador
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada de nueva tarea
        OutlinedTextField(
            value = newTaskText,
            onValueChange = { newTaskText = it },
            label = { Text("Nueva tarea") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (newTaskText.isNotEmpty()) {
                        val newTodo = Todo(
                            id = todos.size + 1,
                            task = newTaskText
                        )
                        viewModel.todos.add(newTodo)
                        newTaskText = ""
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )

        // Botón agregar tarea
        IconButton(
            onClick = {
                if (newTaskText.isNotEmpty()) {
                    val newTodo = Todo(
                        id = todos.size + 1,
                        task = newTaskText
                    )
                    viewModel.todos.add(newTodo)
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



@Composable
fun TodoList(
    todos: List<Todo>,
    onTaskCheckedChange: (Todo) -> Unit,
    onTaskDelete: (Todo) -> Unit
) {
    LazyColumn {
        items(todos) { todo ->
            TodoItem(todo = todo, onTaskCheckedChange = onTaskCheckedChange, onTaskDelete = onTaskDelete)
        }
    }
}


@Composable
fun TodoItem(todo: Todo, onTaskCheckedChange: (Todo) -> Unit, onTaskDelete: (Todo) -> Unit) {
    val checkBoxStates by remember { mutableStateOf("") }
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

            var checked by remember { mutableStateOf( todo.isCompleted) }
            Checkbox(

                checked = checked,
                onCheckedChange = {checked = it },
                modifier = Modifier.padding(end = 16.dp)

            )

            Text(
                text = todo.task,
                style = MaterialTheme.typography.bodyMedium,
                color = if (todo.isCompleted) Color.Gray else Color.Unspecified
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    onTaskDelete(todo)
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.error)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}


