package com.example.http.students

import com.example.api.students.GetAllStudentService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import javax.inject.Inject

class GetAllStudentHttpRequest @Inject constructor(
    private val getAllStudentService: GetAllStudentService
) {
    suspend fun handleGetAllStudents(call: ApplicationCall) {
        try {
            val students = getAllStudentService.invoke(call)
            call.respond(HttpStatusCode.OK, students)

        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Error fetching students: ${e.message}")
        }
    }
}
