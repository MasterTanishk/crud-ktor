package com.example.api.students

import com.example.domain.students.entities.Todolist
import com.example.domain.students.usecases.GetAllStudentServiceImpl
import io.ktor.server.application.*
import javax.inject.Inject

class GetAllStudentService @Inject constructor(private val service: GetAllStudentServiceImpl) {

    suspend fun invoke(call: ApplicationCall): List<Todolist> {
        try {
            val students = service.invoke()
            return students
        } catch (e: Exception) {
            throw e
        }
    }
}
