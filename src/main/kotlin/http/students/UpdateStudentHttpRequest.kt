package com.example.http.students

import com.example.api.students.StudentMapper
import com.example.api.students.UpdateStudentService
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.UpdateRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class UpdateStudentHttpRequest @Inject constructor(
    private val updateStudentService: UpdateStudentService,
    private val studentMapper: StudentMapper
) {

    suspend fun handleUpdateStudent(call: ApplicationCall) {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("Invalid or missing student ID")

            val request = call.receive<UpdateRequest>()

            val updatedStudent = updateStudentService.updateStudent(id, request)

            call.respond(HttpStatusCode.OK, "updated successfully")
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid or missing parameters: ${e.message}")
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
        }
    }
}
