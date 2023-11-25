package com.example.base.ui

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import com.example.base.io.response.ApiService
import com.example.base.io.response.RetrofitClientInstance
import com.example.base.io.response.model.Appointment
import com.example.base.io.response.model.AppointmentPost
import com.example.base.ui.adapters.AppointmentAdapter
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import java.util.Calendar
import javax.security.auth.callback.Callback

class ReserveActivity : AppCompatActivity() {

    lateinit var appointment: AppointmentPost
    val selectedCalendar = Calendar.getInstance()

    //
    lateinit var btnNext2: Button
    lateinit var btnNext: Button
    lateinit var btnConfirm: Button
    lateinit var cvNext : CardView
    lateinit var cvConfirm : CardView
    lateinit var btnVolver2 : Button
    lateinit var cvResumen : CardView
    lateinit var btnVolver3 : Button

    lateinit var etDocente : EditText
    lateinit var spAsignatura : Spinner
    lateinit var rgTrimestre : RadioGroup
    lateinit var etFecha : EditText
    lateinit var rgHora : RadioGroup
    lateinit var rgGrado : RadioGroup
    lateinit var rgSeccion : RadioGroup
    lateinit var etAprendizaje : EditText
    lateinit var etConsideraciones : EditText
    lateinit var rgArticula : RadioGroup
    lateinit var etArticula : EditText

    val retrofit = RetrofitClientInstance.getRetrofitInstance(this)
    val apiService = retrofit.create(ApiService::class.java)


    fun getRadioGroupValue(radioGroup: RadioGroup): String?{
        val selectedRadioButtonID = radioGroup.checkedRadioButtonId

        if (selectedRadioButtonID!=-1){
            val selectedRadioButton = radioGroup.findViewById<RadioButton>(selectedRadioButtonID)
            return selectedRadioButton.tag as? String
        }
        return null
    }

    fun setPostData(){
        appointment.nombre = etDocente.text.toString()
        appointment.asignatura = spAsignatura.selectedItem.toString()

        //Leer valores del RADIO
        appointment.trimestre = getRadioGroupValue(rgTrimestre)

        appointment.fecha = etFecha.text.toString()

        //
        appointment.hora = getRadioGroupValue(rgHora)

        appointment.grado = getRadioGroupValue(rgGrado)
        appointment.seccion = getRadioGroupValue(rgSeccion)

        appointment.aprendizaje=etAprendizaje.text.toString()
        appointment.consideraciones= etConsideraciones.text.toString()


        //TODO: IMPLEMENTAR COMPONENTES
        appointment.estrategias=""
        appointment.observaciones=""
        appointment.descripcion=""


        appointment.articula=etArticula.text.toString()

    }
    fun validateInputs_FST(): Boolean {

        if (etDocente.text.toString().length < 3) {
            etDocente.error = "El nombre es incorrecto"
            return false
        }
        if (spAsignatura.selectedItemPosition == 0) {
            // Si no se ha seleccionado ningún elemento en el Spinner
            Toast.makeText(this, "Selecciona una asignatura", Toast.LENGTH_SHORT).show()
            return false
        }

        if (rgTrimestre.checkedRadioButtonId == -1) {
            // Si ningún RadioButton está seleccionado
            Toast.makeText(this, "Selecciona un trimestre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (etFecha.text.toString().isEmpty()) {
            etFecha.error = ""
            Toast.makeText(this, "Selecciona un Fecha", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


    //Definimos los componentes
    fun setComponents(){
        btnNext2 = findViewById<Button>(R.id.btn_suiguiente2)
        btnNext = findViewById<Button>(R.id.btn_siguiente)
        btnConfirm = findViewById<Button>(R.id.btn_confirmar)
        cvNext = findViewById<CardView>(R.id.cv_siguiente)
        cvConfirm = findViewById<CardView>(R.id.cv_confirmar)
        btnVolver2 = findViewById<Button>(R.id.btn_volver2)
        cvResumen = findViewById<CardView>(R.id.cv_resumen)
        btnVolver3 = findViewById<Button>(R.id.btn_volver3)

        etDocente = findViewById<EditText>(R.id.et_docente)
        spAsignatura = findViewById<Spinner>(R.id.spinner_asignatura)
        rgTrimestre = findViewById<RadioGroup>(R.id.rg_trimestre)
        etFecha = findViewById<EditText>(R.id.et_fecha)
        rgHora = findViewById<RadioGroup>(R.id.rg_hora)
        rgGrado = findViewById<RadioGroup>(R.id.rg_grado)
        rgSeccion = findViewById<RadioGroup>(R.id.rg_seccion)
        etAprendizaje = findViewById<EditText>(R.id.et_aprendizaje)
        etConsideraciones = findViewById<EditText>(R.id.et_consideraciones)
        rgArticula = findViewById<RadioGroup>(R.id.rg_articula)
        etArticula = findViewById<EditText>(R.id.et_articula)
        setRadioAction()
    }

    fun setRadioAction(){
        val radioButton1 = findViewById<RadioButton>(R.id.radio_art_si)
        rgArticula.setOnCheckedChangeListener{
            _, checkedId ->
            if (checkedId == radioButton1.id)
            {
                etArticula.isEnabled = checkedId == radioButton1.id
                etArticula.visibility = View.VISIBLE
                etArticula.setText("")
            }
            else{
                etArticula.isEnabled = false
                etArticula.visibility = View.GONE
                etArticula.setText("Sin comentarios")
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reserve)
        setComponents()

        appointment = AppointmentPost()

        //Codigo de cards views

        btnNext.setOnClickListener {

            if (validateInputs_FST())
            {
                cvNext.visibility = View.GONE
                cvConfirm.visibility = View.VISIBLE
            }
        }
        //card view 2
        btnVolver2.setOnClickListener {

            cvConfirm.visibility = View.GONE
            cvNext.visibility = View.VISIBLE


        }
        btnVolver3.setOnClickListener {
            cvConfirm.visibility = View.VISIBLE
            cvResumen.visibility = View.GONE

        }
        //boton del segundo card que pasa al resumen y es next
        btnNext2.setOnClickListener {
            if (rgHora.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Selecciona un hora", Toast.LENGTH_SHORT).show()
            } else if (rgGrado.checkedRadioButtonId == -1) {
                // Si no se ha seleccionado ningún elemento en el Spinner
                Toast.makeText(this, "Selecciona un grado", Toast.LENGTH_SHORT).show()
            } else if (rgSeccion.checkedRadioButtonId == -1) {
                // Si ningún RadioButton está seleccionado
                Toast.makeText(this, "Selecciona una seccion", Toast.LENGTH_SHORT).show()
            } else if (etAprendizaje.text.toString().length < 15) {
                //etFecha.error = ""
                Toast.makeText(
                    this,
                    "Mencione un aprendizaje esperado adecuado",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (etConsideraciones.text.toString().length < 15) {
                etFecha.error = ""
                Toast.makeText(this, "Menciona alguna consideracion", Toast.LENGTH_SHORT).show()
            } else if (rgArticula.checkedRadioButtonId == -1) {
                // Si ningún RadioButton está seleccionado
                Toast.makeText(this, "¿Articula la reserva con alguna otra?", Toast.LENGTH_SHORT)
                    .show()
            } else {
                showReservaDataToConfirm()
                cvConfirm.visibility = View.GONE
                cvResumen.visibility = View.VISIBLE
            }
        }


        // val spinnerDocente = findViewById<Spinner>(R.id.spinner_docente)
        val spinnerAsignatura = findViewById<Spinner>(R.id.spinner_asignatura)

        val optionAsignatura = arrayOf("", "Español", "Matematicas", "Ciencias")
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionAsignatura)
        spinnerAsignatura.adapter = adapter



        btnConfirm.setOnClickListener {


            setPostData()
            val gson = Gson()
            val json = gson.toJson(appointment)
            val call = apiService.registrarReserva(json)

            call.enqueue(object : retrofit2.Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {

                    Toast.makeText(this@ReserveActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@ReserveActivity, "Error", Toast.LENGTH_SHORT)
                        .show()
                }


            })



            //TODO: Implentar API

            //Toast.makeText(applicationContext, "Clase agendada exitosamente", Toast.LENGTH_SHORT.show()
            //goTousuario()


        }

        //codigo para volver a pantalla usuario #1
        val tvGousuario = findViewById<Button>(R.id.btn_volver1)
        tvGousuario.setOnClickListener {
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
        val i = Intent(this, UsuarioActivity::class.java)
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
