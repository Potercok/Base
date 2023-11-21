package com.example.base.io.response

import android.content.Context
import com.example.base.io.response.authHelpers.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Objeto de acceso
object RetrofitClientInstance {

    //URL de la API
    private val BASE_URL="http://192.168.20.123:8000/api/"
    private var retrofit:Retrofit?=null

    fun getRetrofitInstance(context: Context): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}