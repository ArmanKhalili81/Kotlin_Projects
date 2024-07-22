package com.example.ageinminute

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ButtonSelect = findViewById<Button>(R.id.ButtonSelect)
        val textviewageinminute = findViewById<TextView>(R.id.textViewAgeInMinute)
        val Agetext = findViewById<TextView>(R.id.Agetext)
        val textviewage = findViewById<TextView>(R.id.textViewAge)
        val birthday = findViewById<TextView>(R.id.birthday)
        ButtonSelect.setOnClickListener {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val mounth = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)
            val datepicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, mounth, dayOfmounth ->
                    val date = "${dayOfmounth}/${mounth+1}/${year}"
                    val simpledateformatter = SimpleDateFormat("dd/mm/yyyy")
                    val selecteddate = simpledateformatter.parse(date)
                    var selectdate = selecteddate.time
                    val userdateInminute = TimeUnit.MILLISECONDS.toMinutes(selectdate)

                    val system = System.currentTimeMillis()
                    val systemdate = TimeUnit.MILLISECONDS.toMinutes(system)

                    var AgeInMinute = systemdate - userdateInminute
                    val Age = (TimeUnit.MINUTES.toDays(AgeInMinute))/365
                    when(Age){
                        in 0..3 -> Agetext.setText("! انرژی و سرزندگی !")
                        in 3..6 -> Agetext.setText("! بازیگوشی !")
                        in 6..8 -> Agetext.setText("! تخیل !")
                        in 9..11 -> Agetext.setText("! ابتکار و خلاقیت !")
                        in 12..20 -> Agetext.setText("! شور و اشتیاق !")
                        in 20..35 -> Agetext.setText("! همت و پشتکار !")
                        in 35..50 -> Agetext.setText("! تعمق و ژرق اندیشی !")
                        in 50..80 -> Agetext.setText("! سخاوت و خیرخواهی !")
                        else -> {
                            if(Age>80)
                                Agetext.setText("! خردمندی و فضیلت !")
                        }
                    }
                    birthday.setText(selecteddate.toString())
                    textviewage.setText(Age.toString())
                    textviewageinminute.setText(AgeInMinute.toString())
                },
                year,
                mounth,
                day
            )
            datepicker.datePicker.maxDate = Date().time
            datepicker.show()
        }

    }
}
