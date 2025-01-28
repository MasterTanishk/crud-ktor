package com.example.http.students

import com.example.api.students.GetAStudentService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import javax.inject.Inject

class GetAStudentHttpRequest @Inject constructor(
    private val getAStudentService: GetAStudentService
) {

    suspend fun handleGetStudent(call: ApplicationCall) {
        try {
            val studentId = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("Invalid or missing student ID")
            val student = getAStudentService.getStudent(studentId)
            if (student != null) {
                call.respond(HttpStatusCode.OK, student)
            } else {
                call.respond(HttpStatusCode.NotFound, "Student not found")
            }
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid input")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
        }
    }
}
