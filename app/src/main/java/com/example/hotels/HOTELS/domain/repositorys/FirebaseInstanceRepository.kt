package com.example.hotels.HOTELS.domain.repositorys

import androidx.lifecycle.MutableLiveData
import com.example.hotels.HOTELS.utils.Resources
import com.google.firebase.auth.FirebaseAuth

class FirebaseInstanceRepository :FirebaseInstanceRepositoryInterface {

    val authFirebase = FirebaseAuth.getInstance()

    val result: MutableLiveData<Resources<Boolean>> = MutableLiveData()

    override fun createUserFirebase(email: String, password: String) {
        result.postValue(Resources.Loading())
        authFirebase.createUserWithEmailAndPassword(email, password).
            .addOnSuccessListener {
                Resources.Success()
            }
            .addOnFailureListener {  }
            .addOnCompleteListener {  }
    }

    override fun loginUserFirebase(email: String, password: String) {
        authFirebase.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
            .addOnCompleteListener {  }

    }
}