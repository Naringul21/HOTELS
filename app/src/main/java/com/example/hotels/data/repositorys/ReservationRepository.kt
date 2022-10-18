package com.example.hotels.data.repositorys

import android.app.DatePickerDialog
import java.time.Month
import java.time.MonthDay
import java.time.Year
import java.util.*

class ReservationRepository() {


    private var calendar: Calendar = Calendar.getInstance()

    val pickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMont ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMont)
    }

//    val dataPickerDialog = DatePickerDialog(this@ReservationFragment,
//        pickerListener,
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    )

}