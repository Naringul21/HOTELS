package com.example.hotels.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.data.models.User
import com.example.hotels.HOTELS.domain.repositorys.AuthRepositoryImpl

class PersonViewModel() :ViewModel() {
    private val repo = AuthRepositoryImpl()

    fun getUserInfo(): MutableLiveData<User> {
        val mutableData = MutableLiveData<MutableList<User>>()
        repo.getUser().observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}