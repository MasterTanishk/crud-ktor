package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.repos.StudentRepositoryContract
import javax.inject.Inject

class GetAllStudentServiceImpl @Inject constructor(private val studentRepositoryContract: StudentRepositoryContract) {

    // Fetch all todos
    fun invoke(): List<Todolist> {
        return studentRepositoryContract.fetchAll()
    }
}
