package com.example.myapplication
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.bd.room.TaskDao
import com.example.myapplication.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor (private val taskDao: TaskDao, val taskRepository: TaskRepository) : ViewModel() {

    //val todos: List<TaskEntity> = taskDao.getAll()
private val _state = MutableStateFlow<List<Tarea>>(emptyList())
    val state = _state

    init { viewModelScope.launch {
        taskRepository.getAllTasks().collect{
            _state.emit(it)
        }
    }}
   fun addTask(title: String) {
        viewModelScope.launch {
            val tarea = Tarea(title = title)
            taskRepository.saveTask(tarea)

        }
    }


   fun updateTask(task: Tarea) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }

    fun addTask(task: Tarea) {
        viewModelScope.launch {

        }
    }
    fun deleteTask(task: Tarea) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)

        }
    }





   /* private val _todos: MutableLiveData<List<TaskEntity>> = MutableLiveData()
    val todos: LiveData<List<TaskEntity>> = _todos

    init {
        viewModelScope.launch {
            _todos.postValue(taskDao.getAll())
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            val taskEntity = TaskEntity(title = title)
            taskDao.insertTask(taskEntity)
            _todos.postValue(taskDao.getAll()) // Actualizar la lista después de insertar
        }
    }
    fun addTask(task: TaskEntity) {
        viewModelScope.launch {

            taskDao.insertTask(task)
            _todos.postValue(taskDao.getAll()) // Actualizar la lista después de insertar
        }
    }
    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.updateTask(task)
            _todos.postValue(taskDao.getAll()) // Actualizar la lista después de actualizar
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
            _todos.postValue(taskDao.getAll()) // Actualizar la lista después de eliminar
        }
    }


    */

}
