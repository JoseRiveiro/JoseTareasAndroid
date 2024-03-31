package com.example.myapplication
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "isCompleted") val isCompleted: Boolean = false,
    @ColumnInfo(name = "details") var details: String="Descripción"

)
fun TaskEntity.toDomain() = Tarea(
    id = id,
    title= title,
  isCompleted = isCompleted,
    details = details
)
