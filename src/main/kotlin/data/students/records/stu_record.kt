package com.example.data.students.records

import kotlinx.serialization.Serializable
import java.time.Instant

data class stu_record(
    val id:Int ,
    val data:stu_record_data,
    val createdAt:Instant,
    val updatedAt:Instant
)