package com.example.di

import com.example.api.students.*
import dagger.Component

@Component
interface httpComponent {
    val createStudentService:CreateStudentService
    val getAllStudentService:GetAllStudentService
    val deleteStudentService:DeleteStudentService
    val updateStudentService:UpdateStudentService
    val getAStudentService:GetAStudentService
}