package com.example.base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.Button
import com.example.base.R
import com.example.base.io.response.ApiService
import com.example.base.util.PreferenceHelper
import com.example.base.util.PreferenceHelper.get

import android.widget.EditText
import android.widget.Toast
import com.example.base.io.response.model.LoginResponse
import com.example.base.io.response.RetrofitClientInstance
import com.example.base.io.response.authHelpers.TokenController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val retrofit = RetrofitClientInstance.getRetrofitInstance(this)
    val apiService = retrofit.create(ApiService::class.java)
    //Tkn
    val tknControl = TokenController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //de main a noaccess
        val tvGonoaccess = findViewById<TextView>(R.id.tv_go_to_noaccess)
        tvGonoaccess.setOnClickListener{
            goToNoAccess()
        }
        //validacion con jwt
        val preferences=PreferenceHelper.defaultPrefs(this)
        if (preferences["jwt",""].contains("."))
            goTousuario()
        //ir a pantalla principal
        val tvGousuario= findViewById<Button>(R.id.tv_go_to_usuario)
        tvGousuario.setOnClickListener{
         performLogin()
        }
    }
    //de main a no acces
    private fun goToNoAccess(){
        val i = Intent(this, NoAccess::class.java)
        startActivity(i)
    }
    //de main a ususario
    private fun goTousuario(){
        val i = Intent(this, UsuarioActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun performLogin() {
        val etEmail = findViewById<EditText>(R.id.et_Email).text.toString()
        val etPassword = findViewById<EditText>(R.id.et_Password).text.toString()

        val call = apiService.postLogin(etEmail, etPassword)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()

                // Primero, verifica si la respuesta es nula
                if (loginResponse == null) {
                    Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                    return
                }

                if (response.isSuccessful) {
                    loginResponse.data?.let { data ->
                        tknControl.storeToke(applicationContext, data.jwt)
                        goTousuario()
                    } ?: Toast.makeText(applicationContext, "JWT no encontrado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "No se pudo procesar la solicitud: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}