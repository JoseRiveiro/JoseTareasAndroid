package com.example.myapplication.bd.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.TaskEntity


@Database(entities = [TaskEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao


}