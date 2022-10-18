package com.example.hotels.presentation.ui.activity

import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository

class FirstActivityViewModel() :ViewModel() {
    lateinit var repo: FirebaseInstanceRepository

    fun signOut(){
        repo.signOut()
    }
}