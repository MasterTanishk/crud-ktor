package com.example.routes.students

import com.example.di.httpComponent
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.studentRoutes(HttpComponent:httpComponent){
    get(""){
        HttpComponent.getAllStudentService.invoke(call)
    }
    get("/{id}")
    {
        HttpComponent.getAStudentService.invoke(call)
    }
    post(""){
        HttpComponent.createStudentService.invoke(call)
    }
    put("/{id}"){
        HttpComponent.updateStudentService.invoke(call)
    }
    delete("/{id}"){
        HttpComponent.deleteStudentService.invoke(call)
    }
}