package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.requests.UpdateRequest
import javax.inject.Inject

class UpdateStudentServiceImpl @Inject constructor(private val sturepo:StudentRepo){
    fun invoke(id:Int , req:UpdateRequest){
        sturepo.update(id,req)
    }
}