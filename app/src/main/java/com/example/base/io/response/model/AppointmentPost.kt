package com.example.base.io.response.model

import java.sql.Time
import java.util.Date

data class AppointmentPost(
    var nombre: String? = null,
    var asignatura: String? = null,
    var trimestre: Int? = null,
    var fecha: String? = null,
    var hora: Time? = null,
    var grado: Int? = null,
    var seccion: String? = null,
    var aprendizaje: String? = null,
    var articula: String? = null,
    var estrategias: String? = null
)
