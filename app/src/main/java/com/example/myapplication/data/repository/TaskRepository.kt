package com.example.myapplication.data.repository

import com.example.myapplication.Tarea
import com.example.myapplication.bd.room.TaskDao
import com.example.myapplication.toDomain
import com.example.myapplication.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TaskRepository @Inject constructor(private val taskDao: TaskDao, private val sqlite:TaskDao){

suspend fun getAllTasks(): Flow<List<Tarea>> {
    return withContext(Dispatchers.IO){
     taskDao.getAll().map {list -> list.map { it.toDomain() } }

    }
}

    suspend fun saveTask(tarea: Tarea){
        withContext(Dispatchers.IO){
            taskDao.insertTask(tarea.toEntity())
        }
    }

    suspend fun deleteTask(tarea: Tarea){
        withContext(Dispatchers.IO){
            taskDao.deleteTask(tarea.toEntity())
        }
    }

    suspend fun updateTask(tarea: Tarea){
        withContext(Dispatchers.IO){
            taskDao.updateTask(tarea.toEntity())
        }
    }
}