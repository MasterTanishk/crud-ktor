package com.example.di
import com.example.http.students.CreateStudentHttpRequest
import com.example.http.students.DeleteStudentHttpRequest
import com.example.http.students.GetAStudentHttpRequest
import com.example.http.students.GetAllStudentHttpRequest
import com.example.http.students.UpdateStudentHttpRequest
import dagger.Component

@Component
interface httpComponent {
    val createStudentService:CreateStudentHttpRequest
    val getAllStudentService:GetAllStudentHttpRequest
    val deleteStudentService:DeleteStudentHttpRequest
    val updateStudentService:UpdateStudentHttpRequest
    val getAStudentService:GetAStudentHttpRequest
}