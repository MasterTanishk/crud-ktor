package com.example.domain.students.requests

import kotlinx.serialization.Serializable

@Serializable
data class UpdateRequest(
    val userid:Int,
    val username:String
)