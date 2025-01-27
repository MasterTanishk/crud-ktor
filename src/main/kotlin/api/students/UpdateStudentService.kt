package com.example.api.students

import com.example.domain.students.requests.UpdateRequest
import com.example.domain.students.usecases.UpdateStudentServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class UpdateStudentService @Inject constructor(private val service: UpdateStudentServiceImpl) {

    suspend fun invoke(call: ApplicationCall) {
        try {
            // Get the ID from the path parameter
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("Invalid or missing student ID")

            // Receive the request body as UpdateRequest
            val req = call.receive<UpdateRequest>()

            // Call the service to update the student
            service.invoke(id, req)

            // Respond with OK if successful
            call.respond(HttpStatusCode.OK , "updated successfully")
        } catch (e: IllegalArgumentException) {
            // Handle invalid or missing parameters
            call.respond(HttpStatusCode.BadRequest, "Invalid or missing parameters: ${e.message}")
        } catch (e: Exception) {
            // Log the exception for debugging purposes
            e.printStackTrace()

            // Respond with InternalServerError
            call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
        }
    }
}
