package com.example.hotels.HOTELS.presentation.ui.HomeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.data.db.Hotels
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository

class HomeViewModel() : ViewModel() {
    private val repo = FirebaseInstanceRepository()
    fun fetchData(): LiveData<MutableList<Hotels>> {
        val mutableData = MutableLiveData<MutableList<Hotels>>()
        repo.getHotelData().observeForever { hotelList ->
            mutableData.value = hotelList
        }
        return mutableData
    }

}