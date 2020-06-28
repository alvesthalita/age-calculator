package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActions()
    }

    private fun startActions(){

        btnSelectDate.setOnClickListener{ view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            //Selected date
            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tv_selected_age.text = selectedDate

            //Date in minutes
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val dateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val dateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = dateToMinutes - dateInMinutes

            tv_age_in_minutes.text = differenceInMinutes.toString()

        }, year, month, day)

        datePickerDialog.datePicker.maxDate = Date().time - 86400000
        datePickerDialog.show()
    }
}