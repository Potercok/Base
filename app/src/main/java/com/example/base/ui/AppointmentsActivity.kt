package com.example.base.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.R
import com.example.base.io.response.ApiService
import com.example.base.io.response.RetrofitClientInstance
import com.example.base.model.Appointment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentsActivity : AppCompatActivity() {

    //Arreglo de reservas
    var appointments:  List<Appointment> = mutableListOf()
    //Variables para la API
    val retrofit = RetrofitClientInstance.getRetrofitInstance(this)
    val apiService = retrofit.create(ApiService::class.java)
    lateinit var adapter: AppointmentAdapter
    //Componente
    lateinit var rvAppointments : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)
        rvAppointments = findViewById<RecyclerView>(R.id.rv_appointments)

        getReservas()

        rvAppointments.layoutManager = LinearLayoutManager(this)
        adapter = AppointmentAdapter(appointments)

        rvAppointments.adapter = adapter

        //rvAppointments.adapter = AppointmentAdapter(appointments)
        val tvGoreserve= findViewById<Button>(R.id.tv_go_to_reserve)
        tvGoreserve.setOnClickListener{
            goToreserve()
        }



    }
    private fun getReservas(){

        val call = apiService.getReservas()
        call.enqueue(object : Callback<List<Appointment>> {

            override fun onResponse(call: Call<List<Appointment>>, response: Response<List<Appointment>>) {
                if (response.isSuccessful) {
                    val newAppointments = response.body() ?: emptyList()
                    adapter = AppointmentAdapter(newAppointments)
                    adapter.notifyDataSetChanged()
                    rvAppointments.adapter = adapter
                } else {
                    Toast.makeText(applicationContext,  "Error al obtener datos", Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
                Toast.makeText(applicationContext, "No se pudo procesar la solicitud", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun goToreserve(){
        val i = Intent(this, ReserveActivity::class.java)

        startActivity(i)
        finish()
    }
}