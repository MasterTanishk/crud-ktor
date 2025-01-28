package com.example.domain.students.entities

import kotlinx.serialization.Serializable

@Serializable
data class Todolist(
    val id:Int,
    val userid:Int,
    val username:String
)