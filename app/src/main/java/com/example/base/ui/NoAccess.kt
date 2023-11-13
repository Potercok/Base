package com.example.base.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.base.R

class NoAccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_access)

        val tvGoactivity = findViewById<TextView>(R.id.tv_go_to_exit1)
        tvGoactivity.setOnClickListener{
            goToActivity()
        }
    }
    private fun goToActivity(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

    }
}