package com.example.data.students.repos

import com.example.data.students.tables.RecordMapper
import com.example.data.students.tables.RecordQueries
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.requests.UpdateRequest
import javax.inject.Inject

class StudentRepo @Inject constructor(private val query:RecordQueries ,
    private val mapper:RecordMapper){

    fun create(req:CreateRequest):Todolist {
        val data = mapper.toRecordData(req)
        val record = query.create(data)
        return mapper.fromRecord(record)
    }

    fun fetch(userid:Int):List<Todolist>{
        val record = query.fetch(userid)
        return record.map{mapper.fromRecord(it)}
    }
    fun fetchAll(): List<Todolist> {
        val records = query.fetchAll()  // Fetch all records from the database
        return records.map { mapper.fromRecord(it) }  // Convert the records into Todolist entities
    }
    fun update(id:Int , req:UpdateRequest){
        query.update(id , req)
    }
    fun delete(id:Int){
        query.delete(id)
    }
    fun get(id:Int):Todolist? {
        return query.get(id) ?.let { mapper.toRecord(it) }
            ?.let { mapper.fromRecord(it) }
    }

}