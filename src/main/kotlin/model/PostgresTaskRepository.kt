package com.example.model

import com.example.db.TaskDAO
import com.example.db.TaskTable
import com.example.db.daoToModel
import com.example.db.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PostgresTaskRepository : TaskRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskDAO.all().map(::daoToModel)
    }

    override suspend fun taskByPriority(priority: Priority): List<Task> = suspendTransaction {
        TaskDAO
            .find { (TaskTable.priority eq priority.toString()) }
            .map(::daoToModel)
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        TaskDAO
            .find { (TaskTable.name eq name) }
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun addTask(task: Task): Unit = suspendTransaction {
        TaskDAO.new {
            name = task.name
            description = task.description
            priority = task.priority.toString()
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
        val rowsDeleted = TaskTable.deleteWhere {
            TaskTable.name eq name
        }
        rowsDeleted == 1
    }

    override suspend fun updateTask(taskName: String, updatedTask: Task): Boolean = suspendTransaction {
        // Find the existing task by its name
        val taskToUpdate = TaskDAO.find { (TaskTable.name eq taskName) }.singleOrNull()

        // If task not found, return false
        if (taskToUpdate == null) {
            return@suspendTransaction false
        }

        // Update the task fields
        taskToUpdate.apply {
            name = updatedTask.name
            description = updatedTask.description
            priority = updatedTask.priority.toString()
        }

        // Return true if the task was updated
        true
    }


}