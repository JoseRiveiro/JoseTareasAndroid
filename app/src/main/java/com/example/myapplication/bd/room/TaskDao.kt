package com.example.myapplication.bd.room
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

   // @Query("SELECT * FROM ${TareasDB.TABLE_NAME}")

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<TaskEntity>>

    @Insert
    suspend fun insertTask(task: TaskEntity)
    @Delete
   suspend fun deleteTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)
}