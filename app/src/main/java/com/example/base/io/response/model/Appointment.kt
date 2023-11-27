package com.example.base.io.response.model

import java.util.Date
import java.util.concurrent.RunnableScheduledFuture

data class Appointment(
    val id : Int,
    val nombre : String,
    val dia : Date,
    val hora: String,
)
