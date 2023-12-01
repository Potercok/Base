package com.example.base.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.base.R
import com.example.base.io.response.authHelpers.TokenController

class UsuarioActivity : AppCompatActivity() {
    val tokenController = TokenController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        val btnMidReservas = findViewById<Button>(R.id.btn_mis_reservas)
        btnMidReservas.setOnClickListener{
            goToMyAppoinments()
        }

        val tvGoactivity = findViewById<TextView>(R.id.tv_go_to_exit)
        tvGoactivity.setOnClickListener{
         goToActivity()
        }
        val tvGoreserve= findViewById<Button>(R.id.tv_go_to_reserve)
        tvGoreserve.setOnClickListener{
            goToreserve()
        }

    }
    private  fun goToMyAppoinments (){
        val intent = Intent(this,AppointmentsActivity::class.java)
        startActivity(intent)
    }
    private fun goToActivity(){
        //Implementar cierre de sesion
        tokenController.deleteToken(this)
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

    }
    private fun goToreserve(){
        val i = Intent(this, ReserveActivity::class.java)

        startActivity(i)
        finish()
    }
}