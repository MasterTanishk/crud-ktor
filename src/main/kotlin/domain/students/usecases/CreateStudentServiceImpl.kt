package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import javax.inject.Inject

class CreateStudentServiceImpl @Inject constructor(private val sturepo:StudentRepo){
    fun invoke(req:CreateRequest):Todolist{
        return sturepo.create(req)
    }
}