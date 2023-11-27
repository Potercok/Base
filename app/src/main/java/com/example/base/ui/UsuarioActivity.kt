package com.example.base.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.base.R

class UsuarioActivity : AppCompatActivity() {
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
        val tvGocalendar = findViewById<Button>(R.id.tv_go_to_reserve)
        tvGocalendar.setOnClickListener{
            goToCalendar()
        }

    }
    private fun goToCalendar (){
        val intent = Intent(this,Calendar::class.java)
        startActivity(intent)
    }
    private  fun goToMyAppoinments (){
        val intent = Intent(this,AppointmentsActivity::class.java)
        startActivity(intent)
    }
    private fun goToActivity(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

    }

}