package com.example.hotels.HOTELS.presentation.ui.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.data.repositories.FirestoreRepositoryImpl

class HomeViewModel() : ViewModel() {
    private val repo = FirestoreRepositoryImpl()
    fun fetchData(): LiveData<MutableList<Hotels>> {
        val mutableData = MutableLiveData<MutableList<Hotels>>()
        repo.getHotelData().observeForever { hotelList ->
            mutableData.value = hotelList
        }
        return mutableData
    }
    }

