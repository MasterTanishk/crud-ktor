package com.example.data.students.tables

import com.example.data.students.records.stu_record
import com.example.data.students.records.stu_record_data
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import org.jetbrains.exposed.sql.ResultRow
import javax.inject.Inject

class RecordMapper @Inject constructor(){
    fun toRecord(row: ResultRow) = stu_record(
        id = row[StudentTable.id].value,
        data = toRecordData(row),
        createdAt = row[StudentTable.createdAt],
        updatedAt = row[StudentTable.updatedAt]
    )

    private fun toRecordData(row: ResultRow) = stu_record_data(
        userid = row[StudentTable.userid],
        username = row[StudentTable.username]
    )
    fun toRecordData(data:CreateRequest) = stu_record_data(
        userid = data.userid,
        username = data.username
    )

    fun fromRecord(record:stu_record) = Todolist(
        id = record.id ,
        userid = record.data.userid,
        username = record.data.username
    )
}