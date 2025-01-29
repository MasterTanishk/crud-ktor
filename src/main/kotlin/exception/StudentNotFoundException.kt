package com.example.exception

import kotlinx.serialization.Serializable

@Serializable
class StudentNotFoundException(): TodoException("Student with given id do not exist")