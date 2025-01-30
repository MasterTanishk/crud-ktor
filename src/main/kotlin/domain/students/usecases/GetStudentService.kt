package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.StudentRepositoryContract
import com.example.exception.StudentNotFoundException
import javax.inject.Inject

class GetStudentService @Inject constructor(private val studentRepositoryContract: StudentRepositoryContract) {
    fun invoke(id:Int): Todolist? {
        return studentRepositoryContract.get(id)
    }
}