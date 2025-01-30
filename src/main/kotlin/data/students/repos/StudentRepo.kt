package com.example.data.students.repos

import com.example.data.students.tables.RecordMapper
import com.example.data.students.tables.RecordQueries
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.requests.UpdateRequest
import com.example.domain.students.StudentRepositoryContract
import javax.inject.Inject

class StudentRepo @Inject constructor(private val query:RecordQueries ,
    private val mapper:RecordMapper):StudentRepositoryContract{

    override fun create(request:CreateRequest):Todolist {
        val data = mapper.toRecordData(request)
        val record = query.create(data)
        return mapper.fromRecord(record)
    }

    fun fetch(userid:Int):List<Todolist>{
        val record = query.fetch(userid)
        return record.map{mapper.fromRecord(it)}
    }
    override fun fetchAll(): List<Todolist> {
        val records = query.fetchAll()
        return records.map { mapper.fromRecord(it) }
    }
    override fun update(id:Int , req:UpdateRequest){
        query.update(id , req)
    }
    override fun delete(id:Int){
        query.delete(id)
    }
    override fun get(id:Int):Todolist? {
        return query.get(id) ?.let { mapper.toRecord(it) }
            ?.let { mapper.fromRecord(it) }
    }

}