package com.example.hotels.HOTELS.presentation.ui.sign_up_fragment

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.domain.repositorys.AuthRepositoryImpl

class SignUpViewModel : ViewModel() {

    private var usersRepo = AuthRepositoryImpl()

    private var _isInfosValid = MutableLiveData<Boolean>()
    val isInfosValid: LiveData<Boolean> = _isInfosValid

    private var _isValidMail = MutableLiveData<Boolean>()
    val isValidMail: LiveData<Boolean> = _isValidMail

    private var _isPasswordMatch = MutableLiveData<Boolean>()
    val isPasswordMatch: LiveData<Boolean> = _isPasswordMatch

    private var _isSignUp = MutableLiveData<Boolean>()
    val isSignUp: LiveData<Boolean> = _isSignUp


    fun signUp(
        eMail: String,
        password: String,
        fullname: String,
    ) {

        if (eMail.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
            _isInfosValid.value = false
        } else {

            if (Patterns.EMAIL_ADDRESS.matcher(eMail).matches().not()) {
                _isValidMail.value = false
            } else
                if (password.length < 8) {
                    _isPasswordMatch.value = false

                } else {
                    _isSignUp.value = true
                    usersRepo.signUp(eMail, password, fullname)

                }
        }
    }
}
