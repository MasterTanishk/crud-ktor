package com.example.data.students.tables

import jdk.jfr.internal.event.EventConfiguration.timestamp
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object StudentTable : IntIdTable("students"){
    val userid = integer("userid")
    val username = varchar("username" , 256)
    val createdAt = timestamp("createdAt").default(Instant.now())
    val updatedAt = timestamp("updatedAt").default(Instant.now())
}