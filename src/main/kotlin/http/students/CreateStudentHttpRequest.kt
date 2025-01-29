package com.example.http.students

import com.example.domain.students.usecases.CreateStudentServiceImpl
import com.example.domain.students.requests.CreateRequest
import com.example.exception.TodoException
import com.example.api.students.StudentMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class CreateStudentHttpRequest @Inject constructor(
    private val createStudentService: CreateStudentServiceImpl,
    private val studentMapper: StudentMapper
) {

    suspend fun handleCreateStudent(call: ApplicationCall) {
        try {

            val apiRequest = call.receive<CreateRequest>()

            val domainRequest = studentMapper.toDomain(apiRequest)

            val createdStudent = createStudentService.invoke(domainRequest)

            call.respond(HttpStatusCode.Created, createdStudent)
        } catch (e: TodoException) {
            call.respond(HttpStatusCode.UnprocessableEntity, e.message ?: "Error")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e.message ?: "Internal error")
        }
    }
}
