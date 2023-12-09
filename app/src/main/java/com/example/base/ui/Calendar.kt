package com.example.base.ui

import android.R
import android.os.Bundle
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity


class Calendar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.base.R.layout.activity_calendar)

        val calendarView = findViewById<CalendarView>(com.example.base.R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
        }
    }

}