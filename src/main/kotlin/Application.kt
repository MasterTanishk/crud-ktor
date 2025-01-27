package com.example

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import com.example.routes.students.studentRoutes
import com.example.di.DaggerhttpComponent

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5433/newtodo",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "password"
    )
    val component = DaggerhttpComponent.create()
    embeddedServer(Netty , port = 8085 , host = "0.0.0.0"){
        install(ContentNegotiation){
            json()
        }

        routing{
            get("/"){
                call.respond(HttpStatusCode.OK)
            }
            route("/students"){
                studentRoutes(component)
            }
        }
    }.start(wait = true)
}
