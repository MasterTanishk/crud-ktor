package com.example.routes

import com.example.models.Customer
import com.example.models.customers
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting(){
    route("/customer"){
        get{
            if(customers.isNotEmpty())
            {
                call.respond(customers)
            }
            else
            {
                call.respondText("No customers found" , status = HttpStatusCode.OK)
            }
        }
        get("{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            val customer = customers.find{it.id == id} ?: return@get call.respondText("No customer was found with this id $id", status = HttpStatusCode.OK)
            call.respond(customer)
         }
        post{
            val customer = call.receive<Customer>()
            customers.add(customer)
            call.respondText("Customer created successfully", status = HttpStatusCode.Created)
        }
        delete("{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            if(customers.removeIf{it.id == id}){
                call.respondText("Customer Deleted Successfully!" , status = HttpStatusCode.Accepted)
            }
            else
            {
                call.respondText("not found")
            }
        }
        put("{id?}") {
            val id = call.parameters["id"] ?: return@put call.respondText("Missing id", status = HttpStatusCode.BadRequest)

            // Find the customer with the given id
            val existingCustomer = customers.find { it.id == id }

            if (existingCustomer == null) {
                return@put call.respondText("No customer found with id $id", status = HttpStatusCode.NotFound)
            }

            val updatedCustomer = call.receive<Customer>()

            val index = customers.indexOf(existingCustomer)
            customers[index] = updatedCustomer

            call.respondText("Customer updated successfully", status = HttpStatusCode.OK)
        }

    }
}