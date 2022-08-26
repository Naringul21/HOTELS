package com.example.hotels.HOTELS.presentation.ui.sign_up_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository


class SignUpViewModelFactory(val firebaseRepo: FirebaseInstanceRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(firebaseRepo) as T
    }

}