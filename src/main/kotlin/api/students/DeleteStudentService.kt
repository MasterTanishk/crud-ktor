package com.example.api.students

import com.example.domain.students.usecases.DeleteStudentServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import javax.inject.Inject

class DeleteStudentService @Inject constructor(private val service: DeleteStudentServiceImpl) {

    suspend fun invoke(call: ApplicationCall) {
        try {
            // Get the ID from the path parameter
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("Invalid or missing student ID")

            // Call the service to delete the student
            val isDeleted = service.invoke(id)

            // Respond with No Content if deletion is successful
            call.respondText("deleted successfully!")
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
