
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

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
}

