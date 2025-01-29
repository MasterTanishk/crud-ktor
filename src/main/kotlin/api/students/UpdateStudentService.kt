package com.example.api.students

import com.example.domain.students.requests.UpdateRequest
import com.example.domain.students.usecases.UpdateStudentServiceImpl
import javax.inject.Inject

class UpdateStudentService @Inject constructor(
    private val service: UpdateStudentServiceImpl,
    private val convert: StudentMapper
) {

    fun updateStudent(id: Int, request: UpdateRequest) {
        val conv = convert.toDomain(request)
        service.invoke(id,conv)
    }
}
