package com.example.api.students

import com.example.domain.students.usecases.CreateStudentServiceImpl
import com.example.domain.students.requests.CreateRequest
import javax.inject.Inject

class CreateStudentService
@Inject constructor(private val service: CreateStudentServiceImpl ,
    private val conv:StudentMapper) {

    suspend fun invoke(student: CreateRequest) {
        try {
            val convert = conv.toDomain(student)
            service.invoke(convert)
        } catch (e: Exception) {
            throw e
        }
    }
}
