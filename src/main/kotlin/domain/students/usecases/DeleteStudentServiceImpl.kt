package com.example.domain.students.usecases

import com.example.data.students.repos.StudentRepo
import com.example.domain.students.StudentRepositoryContract
import javax.inject.Inject

class DeleteStudentServiceImpl @Inject constructor(private val studentRepositoryContract: StudentRepositoryContract){
    fun invoke(id:Int){
        return studentRepositoryContract.delete(id)
    }
}