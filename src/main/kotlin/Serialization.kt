package com.example

import com.example.model.Priority
import com.example.model.Task
import com.example.model.TaskRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import org.jetbrains.exposed.sql.*

fun Application.configureSerialization(repository: TaskRepository) {
    install(ContentNegotiation) {
        json()
    }

    routing {
        route("/tasks") {
            // GET all tasks
            get {
                val tasks = repository.allTasks()
                call.respond(tasks)
            }

            // GET task by name
            get("/byname/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                val task = repository.taskByName(name)
                if (task == null) {
                    call.respondText("Not found", status = HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(task)
            }

            // GET tasks by priority
            get("/bypriority/{priority}") {
                val priorityAsText = call.parameters["priority"]
                if (priorityAsText == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                try {
                    val priority = Priority.valueOf(priorityAsText)
                    val tasks = repository.taskByPriority(priority)
                    if (tasks.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }
                    call.respond(tasks)
                } catch (ex: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            // POST (Create) new task
            post {
                try {
                    val task = call.receive<Task>()
                    repository.addTask(task)
                    call.respondText("Successfully added", status = HttpStatusCode.Created)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            // DELETE (Remove) task by name
            delete("/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (repository.removeTask(name)) {
                    call.respondText("Successfully deleted", status = HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            // PUT (Update) existing task
            put("/{taskName}") {
                val name = call.parameters["taskName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest, "Task name is required")
                    return@put
                }
                try {
                    val updatedTask = call.receive<Task>()
                    val existingTask = repository.taskByName(name)

                    if (existingTask == null) {
                        call.respond(HttpStatusCode.NotFound, "Task not found")
                        return@put
                    }

                    // Here, update the existing task with the new data
                    val success = repository.updateTask(name, updatedTask)

                    if (success) {
                        call.respondText("Task successfully updated", status = HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.InternalServerError, "Failed to update task")
                    }
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid task data")
                }
            }
        }
    }
}
