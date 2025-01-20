package com.example

import com.example.model.PostgresTaskRepository
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8083, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    val repository = FakeTaskRepository()
    configureSerialization(PostgresTaskRepository())
    configureDatabases()
    configureRouting()
}
