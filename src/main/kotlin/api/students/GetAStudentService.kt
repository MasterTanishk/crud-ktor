package com.example.api.students

import com.example.domain.students.usecases.GetStudentService
import com.example.domain.students.entities.Todolist
import javax.inject.Inject

class GetAStudentService @Inject constructor(
    private val service: GetStudentService
) {
    fun getStudent(studentId: Int): Todolist? {
        // Call the domain layer to fetch the student data
        return service.invoke(studentId)
    }
}
