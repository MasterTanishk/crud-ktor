package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.repos.StudentRepositoryContract
import com.example.domain.students.requests.UpdateRequest
import javax.inject.Inject

class UpdateStudentServiceImpl @Inject constructor(private val studentRepositoryContract: StudentRepositoryContract){
    fun invoke(id:Int , req:UpdateRequest){
        studentRepositoryContract.update(id,req)
    }
}