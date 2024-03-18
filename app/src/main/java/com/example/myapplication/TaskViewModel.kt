package com.example.myapplication
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.bd.room.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskDao: TaskDao) : ViewModel() {

    val todos: List<TaskEntity> = taskDao.getAll()

   fun addTask(title: String) {
        viewModelScope.launch {
            val taskEntity = TaskEntity(title = title)
            taskDao.insertTask(taskEntity)
        }
    }


   fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }

    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.insertTask(task)
        }
    }
    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.deleteTask(task)

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
