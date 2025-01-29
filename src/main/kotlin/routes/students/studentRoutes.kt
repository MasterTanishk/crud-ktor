package com.example.routes.students

import com.example.di.httpComponent
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.studentRoutes(HttpComponent:httpComponent){
    get(""){
        HttpComponent.getAllStudentService.handleGetAllStudents(call)
    }
    get("/{id}")
    {
        HttpComponent.getAStudentService.handleGetStudent(call)
    }
    post(""){
        HttpComponent.createStudentService.handleCreateStudent(call)
    }
    put("/{id}"){
        HttpComponent.updateStudentService.handleUpdateStudent(call)
    }
    delete("/{id}"){
        HttpComponent.deleteStudentService.handleDeleteStudent(call)
    }
}