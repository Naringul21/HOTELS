package com.example.hotels.HOTELS.presentation.ui.login_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.domain.repositorys.AuthRepositoryImpl

class LogInFragmentViewModel :ViewModel() {
    private var usersRepo = AuthRepositoryImpl()

    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    private var _isInfosValid = MutableLiveData<Boolean>()
    val isInfosValid: LiveData<Boolean> = _isInfosValid

    init {
        _isSignIn = usersRepo.isSignIn
    }

    fun signIn(eMail: String, password: String) {
        if (eMail.isEmpty() || password.isEmpty()) {
            _isInfosValid.value = false
        } else {
            usersRepo.signIn(eMail, password)

        }
    }

    fun signOut(){
       usersRepo.signOut()
    }
}