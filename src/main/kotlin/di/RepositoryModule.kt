//package com.example.di
//
//import com.example.data.students.repos.StudentRepo
//import com.example.domain.students.StudentRepositoryContract
//import dagger.Module
//import dagger.Provides
//import javax.inject.Singleton
//
//@Module
//class RepositoryModule {
//
//    @Provides
//    @Singleton
//    fun provideStudentRepository(studentRepo: StudentRepo): StudentRepositoryContract {
//        return studentRepo  // Return the implementation (StudentRepo)
//    }
//}
