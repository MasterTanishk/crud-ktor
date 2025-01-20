package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id:String ,
    val firstname:String ,
    val secondname:String,
    val email:String
)

val customers = mutableListOf<Customer>(
    Customer("1" , "tan" , "sha" , "tan@gmail.com")
)