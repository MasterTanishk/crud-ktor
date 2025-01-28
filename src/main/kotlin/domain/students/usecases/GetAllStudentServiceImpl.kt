package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import javax.inject.Inject

class GetAllStudentServiceImpl @Inject constructor(private val sturepo: StudentRepo) {

    // Fetch all todos
    fun invoke(): List<Todolist> {
        return sturepo.fetchAll()  // Fetch all todos from the repository
    }
}
