package com.example.domain.students.repos

import com.example.data.students.repos.StudentRepo  // Directly importing data layer repo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.requests.UpdateRequest
import com.example.exception.StudentNotFoundException
import javax.inject.Inject

class StudentRepositoryContract @Inject constructor(
    private val studentRepo: StudentRepo
) {
    fun create(request: CreateRequest): Todolist {
        return studentRepo.create(request)
    }
    fun delete(id:Int){
        studentRepo.delete(id)
    }
    fun fetchAll():List<Todolist>{
        return studentRepo.fetchAll()
    }
    fun get(id:Int):Todolist{
        return studentRepo.get(id)?:throw StudentNotFoundException()
    }
    fun update(id:Int , req:UpdateRequest){
        return studentRepo.update(id,req)
    }

}
