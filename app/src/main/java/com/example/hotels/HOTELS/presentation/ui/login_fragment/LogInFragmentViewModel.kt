package com.example.hotels.HOTELS.presentation.ui.login_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository

class LogInFragmentViewModel :ViewModel() {
    private var usersRepo = FirebaseInstanceRepository()

    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    init {
        _isSignIn = usersRepo.isSignIn
    }

    fun signIn(eMail: String, password: String) {
        usersRepo.signIn(eMail, password)
    }
}