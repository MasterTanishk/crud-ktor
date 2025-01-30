package com.example.domain.students

import com.example.data.students.repos.StudentRepo  // Directly importing data layer repo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.requests.UpdateRequest
import com.example.exception.StudentNotFoundException
import dagger.Component
import dagger.Provides
import javax.inject.Inject

interface StudentRepositoryContract{
    fun create(request: CreateRequest): Todolist
    fun delete(id: Int)
    fun fetchAll(): List<Todolist>
    fun get(id: Int): Todolist?
    fun update(id: Int, req: UpdateRequest)
}
