package com.example.base.io.response

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import com.example.base.io.response.model.LoginResponse
import com.example.base.io.response.model.Appointment
import retrofit2.http.GET


interface ApiService {
    @POST("login/")
    fun postLogin(@Query(value = "email") email: String, @Query(value = "password") password: String):
            Call<LoginResponse>

    @GET("reservas/")
    fun getReservas():
        Call<List<Appointment>>
}