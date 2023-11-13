package com.example.base.io.response

import android.provider.ContactsContract.CommonDataKinds.Email
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import com.example.base.io.response.LoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiService {
    @POST( value ="login")
    fun postLogin(@Query(value="email") email: String, @Query(value = "password") password: String):
            Call<LoginResponse>
    companion object Factory{
        private const val BASE_URL ="http://192.168.100.10:8000/api/"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}