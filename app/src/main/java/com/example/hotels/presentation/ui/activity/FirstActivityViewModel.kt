package com.example.hotels.presentation.ui.activity

import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.domain.repositorys.AuthRepositoryImpl

class FirstActivityViewModel() :ViewModel() {
    lateinit var repo: AuthRepositoryImpl

    fun signOut(){
        repo.signOut()
    }
}