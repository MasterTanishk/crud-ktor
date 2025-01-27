package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import javax.inject.Inject

class DeleteStudentServiceImpl @Inject constructor(private val sturepo:StudentRepo){
    fun invoke(id:Int){
        return sturepo.delete(id)
    }
}