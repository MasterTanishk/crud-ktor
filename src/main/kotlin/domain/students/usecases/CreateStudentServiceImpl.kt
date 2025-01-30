package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.StudentRepositoryContract
import com.example.domain.students.requests.CreateRequest
import javax.inject.Inject
class CreateStudentServiceImpl @Inject constructor(private val studentRepositoryContract: StudentRepositoryContract){
    fun invoke(req:CreateRequest):Todolist{
        return studentRepositoryContract.create(req)
    }
}