package com.example.domain.students.requests

import kotlinx.serialization.Serializable
import javax.print.attribute.standard.RequestingUserName

@Serializable
data class CreateRequest (
    val userid:Int,
    val username: String
)