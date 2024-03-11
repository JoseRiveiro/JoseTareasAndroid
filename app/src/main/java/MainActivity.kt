
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

data class Todo(

    val id: Int,
    val title: String,

    var isCompleted: Boolean = false

)


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoApp()
        }
        
        }

    }

@Composable
//Agregar HiltViewModewl
fun TodoApp(modifier: Modifier = Modifier,
            viewModel: TaskViewModel = hiltViewModel()
) {

    var todos by remember { mutableStateOf<List<TaskEntity>>(emptyList()) }
    var newTaskText by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Lista de tareas
        TodoList(todos = todos, viewModel::updateTask, viewModel::addTask)

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
                        viewModel.addTask(TaskEntity(title = newTaskText))
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
                    viewModel.addTask(TaskEntity(title = newTaskText))
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

    todos: List<TaskEntity>,
    onTaskCheckedChange: (TaskEntity) -> Unit,
    onTaskEdit: (TaskEntity) -> Unit

) {

    LazyColumn {
        items(todos) { task ->
            TodoItem(task, onTaskCheckedChange, onTaskEdit)
        }

    }

}


@Composable
fun TodoItem(todo: TaskEntity, onTaskCheckedChange: (TaskEntity) -> Unit, onTaskEdit: (TaskEntity) -> Unit) {



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
                onCheckedChange = {onTaskCheckedChange(todo)},
                modifier = Modifier.padding(end = 16.dp)

            )

            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyMedium,
                color = if (todo.isCompleted) Color.Gray else Color.Unspecified
            )


            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    onTaskEdit(todo)
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.error)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }

    }

}
@Composable
fun TodoActionIcon(icon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onAddTask: () -> Unit,
    onImeAction: () -> Unit = {},
    onEditComplete: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current


    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text("Nueva tarea") },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (value.isNotEmpty()) {
                    onAddTask()
                    keyboardController?.hide()
                    onImeAction()
                }
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        singleLine = true
    )
}

