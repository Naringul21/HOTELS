package com.example.hotels.HOTELS.presentation.ui.sign_up_fragment

import android.util.Patterns
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import kotlin.contracts.contract

class SignUpViewModel : ViewModel(){

    private var usersRepo = FirebaseInstanceRepository()

    private var _isInfosValid = MutableLiveData<Boolean>()
    val isInfosValid: LiveData<Boolean> = _isInfosValid

    private var _isValidMail = MutableLiveData<Boolean>()
    val isValidMail: LiveData<Boolean> = _isValidMail

    private var _isPasswordMatch = MutableLiveData<Boolean>()
    val isPasswordMatch: LiveData<Boolean> = _isPasswordMatch

    private var _isSignUp = MutableLiveData<Boolean>()
    val isSignUp: LiveData<Boolean> = _isSignUp

    init {
        _isSignUp = usersRepo.isSignUp
    }

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
            } else {
                    usersRepo.signUp(eMail, password, fullname)

                }
            }
        }
    }
