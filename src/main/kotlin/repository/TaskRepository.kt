package com.example.repository

interface TaskRepository {
    fun getTasks(): List<String>
    fun addTask(name: String)
    fun updateTask(id: Int, name: String)
    fun deleteTask(id: Int)
}