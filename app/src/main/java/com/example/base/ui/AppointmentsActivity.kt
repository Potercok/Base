package com.example.base.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.R
import com.example.base.model.Appointment

class AppointmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)
        val rvAppointments = findViewById<RecyclerView>(R.id.rv_appointments)
        val appointments = ArrayList<Appointment>()
        appointments.add(
            Appointment(1,"jorge arreola","16/11/1997","9:00")
        )

        appointments.add(
            Appointment(2,"Liliana gutierrez","01/04/1998","12:00")
        )
        appointments.add(
            Appointment(3,"Diana Laura","11/10/2000","7:00")
        )
        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter = AppointmentAdapter(appointments)
        val tvGoreserve= findViewById<Button>(R.id.tv_go_to_reserve)
        tvGoreserve.setOnClickListener{
            goToreserve()
        }
    }
    private fun goToreserve(){
        val i = Intent(this, ReserveActivity::class.java)

        startActivity(i)
        finish()
    }
}