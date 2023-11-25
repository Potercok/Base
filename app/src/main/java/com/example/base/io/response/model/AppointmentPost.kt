package com.example.base.io.response.model

import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.Date

data class AppointmentPost(
    @SerializedName("nombre")
    var nombre: String? = null,
    @SerializedName("asignatura")
    var asignatura: String? = null,
    @SerializedName("trimestre")
    var trimestre: String? = null,
    @SerializedName("fecha")
    var fecha: String? = null,
    @SerializedName("hora")
    var hora: String? = null,
    @SerializedName("grado")
    var grado: String? = null,
    @SerializedName("seccion")
    var seccion: String? = null,
    @SerializedName("aprendizaje")
    var aprendizaje: String? = null,
    @SerializedName("articula")
    var articula: String? = null,
    @SerializedName("estrategias")
    var estrategias: String? = null,
    @SerializedName("descripcion")
    var descripcion: String? = null,
    @SerializedName("observaciones")
    var observaciones: String? = null,
    @SerializedName("consideraciones")
    var consideraciones: String? = null
)
