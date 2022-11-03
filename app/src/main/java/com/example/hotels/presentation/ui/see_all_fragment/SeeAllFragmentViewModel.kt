package com.example.hotels.HOTELS.presentation.ui.see_all_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.data.repositories.FirestoreRepositoryImpl

class SeeAllFragmentViewModel:ViewModel() {
    private val repo = FirestoreRepositoryImpl()
    fun getDataSeeAll(): LiveData<MutableList<Hotels>> {
        val mutableData = MutableLiveData<MutableList<Hotels>>()
        repo.getHotelData().observeForever { hotelList ->
            mutableData.value = hotelList
        }
        return mutableData
    }


}