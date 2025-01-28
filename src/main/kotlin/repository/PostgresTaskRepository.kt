package com.example.repository

import java.sql.SQLException
import javax.inject.Inject
import javax.sql.DataSource

class PostgresTaskRepository @Inject constructor(private val dataSource: DataSource) : TaskRepository {
    override fun getTasks(): List<String> {
        dataSource.connection.use { connection ->
            val resultSet = connection.prepareStatement("SELECT * FROM tasks").executeQuery()
            val tasks = mutableListOf<String>()
            while (resultSet.next()) {
                tasks.add(resultSet.getString("name"))
            }
            return tasks
        }
    }

    override fun addTask(name: String) {
        dataSource.connection.use { connection ->
            connection.prepareStatement("INSERT INTO tasks (name) VALUES (?)").apply {
                setString(1, name)
                executeUpdate()
            }
        }
    }
    override fun updateTask(id: Int, name: String) {
        dataSource.connection.use { connection ->
            connection.prepareStatement("UPDATE tasks SET name = ? WHERE id = ?").apply {
                setString(1, name)
                setInt(2, id)
                executeUpdate()
            }
        }
    }
    override fun deleteTask(id: Int) {
        dataSource.connection.use { connection ->
            connection.autoCommit = false
            try {

                connection.prepareStatement(
                    "UPDATE tasks SET deleted_at = NOW() WHERE id = ?"
                ).apply {
                    setInt(1, id)
                    executeUpdate()
//                }

                connection.prepareStatement("DELETE FROM tasks WHERE id = ?").apply {
                    setInt(1, id)
                    executeUpdate()
                }
                connection.commit()
            } }
            catch (ex: SQLException) {
                connection.rollback()
                throw ex
            }
        }
    }



}