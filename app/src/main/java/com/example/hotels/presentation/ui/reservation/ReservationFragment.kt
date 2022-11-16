package com.example.hotels.presentation.ui.reservation

import android.app.DatePickerDialog
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
            initSpinner()
        }


    }

    fun initSpinner(){
        val adults = resources.getStringArray(R.array.adults)
        val children = resources.getStringArray(R.array.children)


        if (spinner_adults != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, adults)
            spinner_adults.adapter = adapter
        }

        if (spinner_adults != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, children)
            spinner_children.adapter = adapter
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