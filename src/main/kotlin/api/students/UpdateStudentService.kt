package com.example.api.students

import com.example.domain.students.requests.UpdateRequest
import com.example.domain.students.usecases.UpdateStudentServiceImpl
import javax.inject.Inject

class UpdateStudentService @Inject constructor(
    private val service: UpdateStudentServiceImpl
) {

    fun updateStudent(id: Int, request: UpdateRequest) {
        service.invoke(id,request)
    }
}
