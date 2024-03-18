package com.example.myapplication.data.repository

import com.example.myapplication.bd.room.TaskDao
import javax.inject.Inject


class TaskRepository @Inject constructor(private val taskDao: TaskDao){


}