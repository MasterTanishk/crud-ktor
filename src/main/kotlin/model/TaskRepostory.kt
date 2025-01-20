package com.example.model

interface TaskRepository{
    suspend fun allTasks() : List<Task>
    suspend fun taskByPriority(priority: Priority):List<Task>
    suspend fun taskByName(name:String):Task?
    suspend fun addTask(task:Task)
    suspend fun removeTask(name:String):Boolean
    suspend fun updateTask(name:String , task: Task):Boolean
}