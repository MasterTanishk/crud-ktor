package com.example.api.students

import com.example.domain.students.usecases.GetAllStudentServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import javax.inject.Inject

class GetAllStudentService @Inject constructor(private val service: GetAllStudentServiceImpl) {

    // Endpoint to fetch all todos
    suspend fun invoke(call: ApplicationCall) {
        try {
            val todos = service.getAllTodos()  // Get all todos from the service
            call.respond(HttpStatusCode.OK, todos)  // Respond with all todos as JSON
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Error fetching todos")
        }
    }
}
