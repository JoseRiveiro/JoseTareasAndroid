package domain

import TaskEntity

data class Tarea (
    val id: Long,
    val title: String,

    var isCompleted: Boolean = false
)
fun Tarea.toEntity() = TaskEntity(

   id = id,
    title = title,
   isCompleted = isCompleted


)