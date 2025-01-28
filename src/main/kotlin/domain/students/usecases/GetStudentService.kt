package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.exception.StudentNotFoundException
import javax.inject.Inject

class GetStudentService @Inject constructor(private val sturepo:StudentRepo) {
    fun invoke(id:Int):Todolist{
        return sturepo.get(id)?: throw StudentNotFoundException()
    }
}