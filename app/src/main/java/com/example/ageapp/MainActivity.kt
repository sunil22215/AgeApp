package com.example.ageapp

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Month
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSelectDate.setOnClickListener {view->
            clickDatePicker(view)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickDatePicker(view: View) {
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view,year, month, dayOfMonth ->
            Toast.makeText(this,"the chosen year is $year,the chosen month is $month,the chosen day is $dayOfMonth",
                 Toast.LENGTH_SHORT).show()

            val selectDate="$dayOfMonth/${month+1}/$year"

            textViewSelectDate.setText(selectDate)
            val sdf=SimpleDateFormat("dd/MM/yy",Locale.ENGLISH)
            val theDate=sdf.parse(selectDate)

            val selectDateInMinute=theDate!!.time/ 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes = currentDate!!.time/ 60000

            val differeceInMinute = currentDateInMinutes - selectDateInMinute
            Log.d("age",differeceInMinute.toString())

           val ageInDay= differeceInMinute/ 525600
            Log.d("age",ageInDay.toString())

            textViewAge.setText(ageInDay.toString())

        }
            ,year,month,day).show()




    }
}
