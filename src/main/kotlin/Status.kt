package com.example

import kotlinx.serialization.Serializable

@Serializable
enum class Status {
    BUILDING , PROGRESS , DONE
}