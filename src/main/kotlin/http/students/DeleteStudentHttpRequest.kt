package com.example.http.students

import com.example.api.students.DeleteStudentService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class DeleteStudentHttpRequest @Inject constructor(
    private val deleteStudentService: DeleteStudentService
) {

    suspend fun handleDeleteStudent(call: ApplicationCall) {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null || id <= 0) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing student ID")
                return
            }
            deleteStudentService.invoke(id)

            call.respond(HttpStatusCode.NoContent, "Student deleted successfully!")
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid parameters: ${e.message}")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
        }
    }
}
