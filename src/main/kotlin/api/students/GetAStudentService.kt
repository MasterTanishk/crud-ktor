package com.example.api.students

import com.example.domain.students.usecases.GetStudentService
import com.example.domain.students.entities.Todolist
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import javax.inject.Inject

class GetAStudentService @Inject constructor(private val service: GetStudentService) {

    suspend fun invoke(call: ApplicationCall) {
        try {
            // Get student ID from the path parameter
            val studentId = call.parameters["id"]?.toIntOrNull()

            // Check if ID is valid, otherwise return a Bad Request response
            if (studentId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing student ID")
                return
            }

            // Call the service to get the student information
            val student = service.invoke(studentId)

            // If student is found, respond with the student data
            if (student != null) {
                call.respond(HttpStatusCode.OK, student)
            } else {
                // If no student is found, respond with Not Found
                call.respond(HttpStatusCode.NotFound, "Student not found")
            }
        } catch (e: Exception) {
            // Handle any other exceptions and respond with an Internal Server Error
            call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
        }
    }
}
