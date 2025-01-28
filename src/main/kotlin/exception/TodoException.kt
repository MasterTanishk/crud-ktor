package com.example.exception

import kotlinx.serialization.Serializable

@Serializable
open class TodoException(override val message:String):Exception()