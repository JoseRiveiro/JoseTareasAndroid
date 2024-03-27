package com.example.myapplication

data class Tarea (
    var id: Long? = 0,
    val title: String,
    var isCompleted: Boolean = false,
    var details: String = " "
)
fun Tarea.toEntity() = TaskEntity(

   id = id?:0,
    title = title,
   isCompleted = isCompleted,
    details = details


)