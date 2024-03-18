package com.example.myapplication

data class Tarea (
    val id: Long? = null,
    val title: String,

    var isCompleted: Boolean = false
)
fun Tarea.toEntity() = TaskEntity(

   id = id?:0,
    title = title,
   isCompleted = isCompleted


)