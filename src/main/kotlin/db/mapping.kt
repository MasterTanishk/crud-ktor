package com.example.db

import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Transaction
import com.example.model.Priority
import com.example.model.Task
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object TaskTable : IntIdTable("task"){
    val name = varchar("name" , 50)
    val descrption = varchar("description" , 80)
    val priority = varchar("priority" , 40)
}

class TaskDAO(id:EntityID<Int>):IntEntity(id){
    companion object : IntEntityClass<TaskDAO>(TaskTable)
    var name by TaskTable.name
    var description by TaskTable.descrption
    var priority by TaskTable.priority

}

suspend fun <T> suspendTransaction(block: Transaction.() -> T):
T = newSuspendedTransaction(Dispatchers.IO , statement = block)
fun daoToModel(dao:TaskDAO) = Task(
    dao.name,
    dao.description,
    Priority.valueOf(dao.priority)
)
