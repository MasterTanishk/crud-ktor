package com.example.api.students

import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.usecases.CreateStudentServiceImpl
import com.example.exception.TodoException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.lang.Exception
import javax.inject.Inject

class CreateStudentService
    @Inject constructor(private val service:CreateStudentServiceImpl){
    suspend fun invoke(call:ApplicationCall){
        try{
            val req = call.receive<CreateRequest>()
            val res = service.invoke(req)
            call.respond(HttpStatusCode.OK, "created successfully!")
        } catch (e: TodoException){
            println("Error is ${e.message}")
            call.respond(HttpStatusCode.UnprocessableEntity, e)
        }
        catch (e: Exception) {
            println("Error is ${e.message}")
            call.respond(HttpStatusCode.InternalServerError, e)
        }
    }
}