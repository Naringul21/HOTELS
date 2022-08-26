package com.example.hotels.HOTELS.presentation.ui.sign_up_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository
import com.example.hotels.HOTELS.utils.Resources

class SignUpViewModel(val firebaseRepo: FirebaseInstanceRepository) : ViewModel(){



    fun setLogin(email: String, password: String){
        firebaseRepo.loginUserFirebase(email, password)
    }
}