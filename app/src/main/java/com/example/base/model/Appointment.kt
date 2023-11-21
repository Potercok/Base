package com.example.base.model

import java.util.Date
import java.util.concurrent.RunnableScheduledFuture

data class Appointment(
    val id : Int,
    val nombre : String,
    val fecha : Date,
    val hora_inicio: String,
    val hora_fin: String
)
