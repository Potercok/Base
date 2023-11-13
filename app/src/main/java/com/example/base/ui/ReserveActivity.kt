package com.example.base.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.base.R
import org.w3c.dom.Text
import java.util.Calendar

class ReserveActivity : AppCompatActivity() {
    val selectedCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reserve)
        val btnNext2 = findViewById<Button>(R.id.btn_suiguiente2)
        val btnNext = findViewById<Button>(R.id.btn_siguiente)
        val btnConfirm =findViewById<Button>(R.id.btn_confirmar)
        val cvNext = findViewById<CardView>(R.id.cv_siguiente)
        val cvConfirm = findViewById<CardView>(R.id.cv_confirmar)
        val btnVolver2 = findViewById<Button>(R.id.btn_volver2)
        val cvResumen = findViewById<CardView>(R.id.cv_resumen)
        val btnVolver3 = findViewById<Button>(R.id.btn_volver3)

        val etDocente = findViewById<EditText>(R.id.et_docente)
        val spAsignatura= findViewById<Spinner>(R.id.spinner_asignatura)
        val  rgTrimestre = findViewById<RadioGroup>(R.id.rg_trimestre)
        val etFecha = findViewById<EditText>(R.id.et_fecha)
        val rgHora = findViewById<RadioGroup>(R.id.rg_hora)
        val rgGrado = findViewById<RadioGroup>(R.id.rg_grado)
        val rgSeccion = findViewById<RadioGroup>(R.id.rg_seccion)
        val etAprendizaje = findViewById<EditText>(R.id.et_aprendizaje)
        val  etConsideraciones = findViewById<EditText>(R.id.et_consideraciones)
        val  rgArticula = findViewById<RadioGroup>(R.id.rg_articula)

        //Codigo de cards views

        btnNext.setOnClickListener{
            if (etDocente.text.toString().length<3){
                etDocente.error="El nombre es incorrecto"
            } else if (spAsignatura.selectedItemPosition == 0) {
                // Si no se ha seleccionado ningún elemento en el Spinner
                Toast.makeText(this, "Selecciona una asignatura", Toast.LENGTH_SHORT).show()
            } else if (rgTrimestre.checkedRadioButtonId == -1) {
                // Si ningún RadioButton está seleccionado
                Toast.makeText(this, "Selecciona un trimestre", Toast.LENGTH_SHORT).show()
            }else if (etFecha.text.toString().isEmpty()) {
                etFecha.error = ""
                Toast.makeText(this, "Selecciona un Fecha", Toast.LENGTH_SHORT).show()
            }else{
                cvNext.visibility=View.GONE
                cvConfirm.visibility=View.VISIBLE
            }
        }
        //card view 2
        btnVolver2.setOnClickListener{

                cvConfirm.visibility=View.GONE
                cvNext.visibility=View.VISIBLE


        }
        btnVolver3.setOnClickListener{
            cvConfirm.visibility=View.VISIBLE
            cvResumen.visibility=View.GONE

        }
        //boton del segundo card que pasa al resumen y es next
        btnNext2.setOnClickListener{
            if (rgHora.checkedRadioButtonId == -1){
                Toast.makeText(this, "Selecciona un hora", Toast.LENGTH_SHORT).show()
            } else if (rgGrado.checkedRadioButtonId == -1) {
                // Si no se ha seleccionado ningún elemento en el Spinner
                Toast.makeText(this, "Selecciona un grado", Toast.LENGTH_SHORT).show()
            } else if (rgSeccion.checkedRadioButtonId == -1) {
                // Si ningún RadioButton está seleccionado
                Toast.makeText(this, "Selecciona una seccion", Toast.LENGTH_SHORT).show()
            }else if (etAprendizaje.text.toString().length<15) {
                etFecha.error = ""
                Toast.makeText(this, "Mencione un aprendizaje esperado adecuado", Toast.LENGTH_SHORT).show()
            }else if (etConsideraciones.text.toString().length<15) {
                etFecha.error = ""
                Toast.makeText(this, "Menciona alguna consideracion", Toast.LENGTH_SHORT).show()
            } else if (rgArticula.checkedRadioButtonId == -1) {
                // Si ningún RadioButton está seleccionado
                Toast.makeText(this, "¿Articula la reserva con alguna otra?", Toast.LENGTH_SHORT).show()
            }else {
                showReservaDataToConfirm()
                cvConfirm.visibility = View.GONE
                cvResumen.visibility = View.VISIBLE
            }
        }


       // val spinnerDocente = findViewById<Spinner>(R.id.spinner_docente)
        val spinnerAsignatura = findViewById<Spinner>(R.id.spinner_asignatura)

        val optionAsignatura = arrayOf("","Español","Matematicas","Ciencias")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionAsignatura)
        spinnerAsignatura.adapter = adapter



        btnConfirm.setOnClickListener{
            Toast.makeText(applicationContext, "Clase agendada exitosamente", Toast.LENGTH_SHORT).show()
            goTousuario()
        }

        //codigo para volver a pantalla usuario #1
        val tvGousuario= findViewById<Button>(R.id.btn_volver1)
        tvGousuario.setOnClickListener{
            goTousuario()
        }
    }
    private fun showReservaDataToConfirm(){
        val tvConfirmDocente = findViewById<TextView>(R.id.tv_resumen_docente)
        val tvConfirmAsignatura = findViewById<TextView>(R.id.tv_resumen_asignatura)
        val tvConfirmDia = findViewById<TextView>(R.id.tv_resumen_dia)
        val tvConfirmHora = findViewById<TextView>(R.id.tv_resumen_hora)
        val tvConfirmGrado = findViewById<TextView>(R.id.tv_resumen_grado)

        val etDocente = findViewById<EditText>(R.id.et_docente)
        val spAsignatura= findViewById<Spinner>(R.id.spinner_asignatura)
        val  rgTrimestre = findViewById<RadioGroup>(R.id.rg_trimestre)
        val etFecha = findViewById<EditText>(R.id.et_fecha)
        val rgHora = findViewById<RadioGroup>(R.id.rg_hora)
        val rgGrado = findViewById<RadioGroup>(R.id.rg_grado)
        val rgSeccion = findViewById<RadioGroup>(R.id.rg_seccion)
        val etAprendizaje = findViewById<EditText>(R.id.et_aprendizaje)
        val  etConsideraciones = findViewById<EditText>(R.id.et_consideraciones)
        val  rgArticula = findViewById<RadioGroup>(R.id.rg_articula)

        tvConfirmDocente.text=etDocente.text.toString()
        tvConfirmAsignatura.text=spAsignatura.selectedItem.toString()

        val selectRadioBtnId = rgHora.checkedRadioButtonId
        val selectedRadioType = rgHora.findViewById<RadioButton>(selectRadioBtnId)
        tvConfirmHora.text = selectedRadioType.text.toString()
        tvConfirmDia.text = etFecha.text.toString()

        val selectRadioBtnId2 = rgGrado.checkedRadioButtonId
        val selectedRadioType2 = rgGrado.findViewById<RadioButton>(selectRadioBtnId2)
        tvConfirmGrado.text = selectedRadioType2.text.toString()



    }
    //Codigo para volver #1
    private fun Int.twoDigits()
            =if (this > 10) this.toString() else "0$this"

    private fun goTousuario(){
        val i = Intent(this, usuario::class.java)
        startActivity(i)
        finish()
    }
    fun onClickScheduledDate(v:View?){
        val etScheduledDate = findViewById<EditText>(R.id.et_fecha)

        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener{view: DatePicker?, y: Int, m: Int, d: Int ->
            selectedCalendar.set(y,m,d)
            etScheduledDate.setText(resources.getString(R.string.date_format,
                y,
                (m+1).twoDigits(),
                d.twoDigits()
            ))
            etScheduledDate.error=null
        }
        DatePickerDialog(this,listener,year,month,dayOfMonth).show()
    }

    override fun onBackPressed() {

        val cvNext = findViewById<CardView>(R.id.cv_siguiente)
        val cvConfirm = findViewById<CardView>(R.id.cv_confirmar)
        val cvResumen = findViewById<CardView>(R.id.cv_resumen)

        if (cvResumen.visibility == View.VISIBLE){
            cvResumen.visibility = View.GONE
            cvConfirm.visibility = View.VISIBLE
        }else if (cvConfirm.visibility == View.VISIBLE){
            cvConfirm.visibility = View.GONE
            cvNext.visibility = View.VISIBLE
        }else if (cvNext.visibility == View.VISIBLE){
            goTousuario()
        }



    }
}
