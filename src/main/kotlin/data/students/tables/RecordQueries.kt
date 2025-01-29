package com.example.data.students.tables

import com.example.data.students.records.stu_record
import com.example.data.students.records.stu_record_data
import com.example.data.students.tables.StudentTable.select
import com.example.domain.students.requests.UpdateRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import javax.inject.Inject

class RecordQueries @Inject constructor(private val mapper: RecordMapper) {
    fun create(data: stu_record_data): stu_record = transaction {
        val resultRow = StudentTable.insert {
            it[userid] = data.userid
            it[username] = data.username
            it[createdAt] = Instant.now()
            it[updatedAt] = Instant.now()
        }.resultedValues?.firstOrNull()

        resultRow?.let { mapper.toRecord(it) }
            ?: throw IllegalStateException("Insert operation failed, no row returned.")
    }

    fun fetch(userid: Int): List<stu_record> = transaction {
        StudentTable.selectAll()
            .filter { it[StudentTable.userid] == userid }
            .map { mapper.toRecord(it) }
    }

    fun fetchAll(): List<stu_record> = transaction {
        StudentTable.selectAll()  // Select all rows from the table
            .map { mapper.toRecord(it) }  // Map each row to a `stu_record` object
    }

    fun update(id: Int, req: UpdateRequest) {
        val rowsUpdated = transaction {
            StudentTable.update({ StudentTable.id eq id }) {
                it[userid] = req.userid
                it[username] = req.username
                it[updatedAt] = Instant.now()
            }
        }
        if (rowsUpdated == 0) {
            throw IllegalStateException("Update operation failed, no rows updated.")
        }
    }

    fun delete(id: Int) {
        val rowsDeleted = transaction {
            StudentTable.deleteWhere { StudentTable.id eq id }
        }
        if (rowsDeleted == 0) {
            throw IllegalStateException("Delete operation failed, no rows deleted.")
        }
    }

    fun get(id: Int): ResultRow? = transaction {
        StudentTable.selectAll().where { StudentTable.id eq id }.singleOrNull()
    }
}
