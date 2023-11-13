package com.example.base.model

import java.util.concurrent.RunnableScheduledFuture

data class Appointment(
    val id : Int,
    val doctorname : String,
    val scheduledDate:String,
    val scheduledTime:String

)
