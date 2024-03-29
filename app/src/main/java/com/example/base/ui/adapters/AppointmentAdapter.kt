package com.example.base.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.base.R
import com.example.base.io.response.model.Appointment

class AppointmentAdapter (private val appointments:List<Appointment>)
    : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>(){
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tvAppointmentId = itemView.findViewById<TextView>(R.id.tv_id)
        val tvDoctorName = itemView.findViewById<TextView>(R.id.tv_medico)
        val tvScheduledDate = itemView.findViewById<TextView>(R.id.tv_fecha)
        val tvScheduledTime = itemView.findViewById<TextView>(R.id.tv_hora)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_appoinment,parent,false)
        )
    }

    override fun getItemCount() = appointments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.tvAppointmentId.text = "Reserva #${appointment.id}"
        holder.tvDoctorName.text = appointment.nombre
        holder.tvScheduledDate.text = "Reservado el dia: ${appointment.dia}"
        holder.tvScheduledTime.text = "hora de la reserva: ${appointment.hora}"
    }
}