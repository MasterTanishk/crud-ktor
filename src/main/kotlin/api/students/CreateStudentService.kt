package com.example.api.students

import com.example.domain.students.usecases.CreateStudentServiceImpl
import com.example.domain.students.requests.CreateRequest
import javax.inject.Inject

class CreateStudentService
@Inject constructor(private val service: CreateStudentServiceImpl) {

    suspend fun invoke(student: CreateRequest) {
        try {
            service.invoke(student)
        } catch (e: Exception) {
            throw e
        }
    }
}
