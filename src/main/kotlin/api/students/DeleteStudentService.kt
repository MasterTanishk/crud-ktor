package com.example.api.students

import com.example.domain.students.usecases.DeleteStudentServiceImpl
import io.ktor.server.application.*
import javax.inject.Inject

class DeleteStudentService @Inject constructor(private val service: DeleteStudentServiceImpl) {

    suspend fun invoke(id: Int) {
        try {
            service.invoke(id)
        } catch (e: Exception) {
            throw e
        }
    }
}
