package com.example.base.io.response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Objeto de acceso
object RetrofitClientInstance {

    //URL de la API
    private val BASE_URL="http://192.168.1.7:8000/api/"
    private var retrofit:Retrofit?=null

     val retrofitClientInstance: Retrofit
        get(){
            if (retrofit==null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

}