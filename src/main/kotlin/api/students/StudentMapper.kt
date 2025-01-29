package com.example.api.students
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.requests.UpdateRequest
import javax.inject.Inject

class StudentMapper @Inject constructor(){

    fun toDomain(request: CreateRequest): CreateRequest {
        return CreateRequest(
            userid = request.userid,
            username = request.username
        )
    }

    fun toApi(request: CreateRequest): CreateRequest {
        return CreateRequest(
            userid = request.userid,
            username = request.username
        )
    }

    fun toDomain(request: UpdateRequest): UpdateRequest {
        return UpdateRequest(
            userid = request.userid,
            username = request.username
        )
    }

    fun toApi(request: UpdateRequest): UpdateRequest{
        return UpdateRequest(
            userid = request.userid,
            username = request.username
        )
    }
}
