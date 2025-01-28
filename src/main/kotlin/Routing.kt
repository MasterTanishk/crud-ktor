package com.example
import com.example.dagger.DaggerAppComponent
import com.example.repository.TaskRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value

fun Application.configureRouting() {
    val comp  = DaggerAppComponent.create()
    val taskRepository: TaskRepository = comp.getTaskRepository()
    val task = mutableListOf<Task>()
    routing {
        get("/tasks") {
            call.respond(taskRepository.getTasks())
        }

        post("/tasks") {
            val taskName = call.receive<Task>()?: return@post call.respondText("Missing task name", status = HttpStatusCode.BadRequest)
            taskRepository.addTask(taskName.name)
            call.respondText("Task created", status = HttpStatusCode.Created)
        }

        put("/tasks/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respondText("Invalid ID", status = HttpStatusCode.BadRequest)
            val taskName = call.receive<Task>().name ?: return@put call.respondText("Missing task name", status = HttpStatusCode.BadRequest)
            if(taskName.isEmpty()) return@put call.respondText("Task name is empty", status = HttpStatusCode.BadRequest)
            taskRepository.updateTask(id, taskName)
            call.respondText("Task updated", status = HttpStatusCode.OK)
        }

        delete("/tasks/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText("Invalid ID", status = HttpStatusCode.BadRequest)
            taskRepository.deleteTask(id)
            call.respondText("Task deleted", status = HttpStatusCode.OK)
        }
    }
}

@Serializable
data class Task(val name: String)
