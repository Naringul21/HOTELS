package com.example.hotels.presentation.ui

import android.app.DatePickerDialog
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotels.R
import com.example.hotels.databinding.FragmentReservationBinding
import com.example.hotels.databinding.FragmentSignUpBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_reservation.*
import java.text.SimpleDateFormat
import java.util.*

class ReservationFragment : Fragment(R.layout.fragment_reservation) {
    private var _binding: FragmentReservationBinding? = null
    private val binding: FragmentReservationBinding get() = _binding!!
    lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.regCheckIn.setOnClickListener {
            showDataRangePicker()
        }
    }

    private fun showDataRangePicker() {

        val dateRangePicker =
            MaterialDatePicker
                .Builder.dateRangePicker()
                .setTitleText("Select Date")
                .setTheme(R.style.Theme_YV_Bible_DatePicker)
                .build()

        dateRangePicker.show(
            childFragmentManager,
            "date_range_picker"
        )

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->

            val startDate = dateSelected.first
            val endDate = dateSelected.second

            if (startDate != null && endDate != null) {
                binding.regCheckIn.text =
                    "${convertLongToTime(startDate)}"
                binding.regCheckOut.text = "${convertLongToTime(endDate)}"
            }
        }

    }


    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault())
        return format.format(date)
    }
}