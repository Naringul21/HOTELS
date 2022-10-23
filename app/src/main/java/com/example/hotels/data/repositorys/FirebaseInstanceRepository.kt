package com.example.hotels.HOTELS.domain.repositorys

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hotels.HOTELS.data.models.FirebaseUserModel
import com.example.hotels.HOTELS.utils.Constants.COLLECTION_PATH
import com.example.hotels.HOTELS.utils.Constants.E_MAIL
import com.example.hotels.HOTELS.utils.Constants.FAILURE
import com.example.hotels.HOTELS.utils.Constants.FULLNAME
import com.example.hotels.HOTELS.utils.Constants.ID
import com.example.hotels.HOTELS.utils.Constants.PASSWORD
import com.example.hotels.HOTELS.utils.Constants.SIGN_IN
import com.example.hotels.HOTELS.utils.Constants.SIGN_UP
import com.example.hotels.HOTELS.utils.Constants.SUCCESS
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseInstanceRepository() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    var isSignIn = MutableLiveData<Boolean>()

    var isSignUp = MutableLiveData<Boolean>()

    var userInfo = MutableLiveData<FirebaseUserModel>()

    private val auth = Firebase.auth
    private val db = Firebase.firestore

    fun signIn(eMail: String, password: String) {

        coroutineScope.launch {
            auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    isSignIn.postValue(true)
                    Log.d(SIGN_IN, SUCCESS)
                } else {
                    isSignIn.postValue(false)
                    Log.w(SIGN_IN, FAILURE, task.exception)
                }
            }
        }
    }

    fun signUp(eMail: String, password: String, fullname: String) {
        coroutineScope.launch {
            auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val currentUser = auth.currentUser
                    currentUser?.let { fbUser ->
                        val user = hashMapOf(
                            ID to fbUser.uid,
                            E_MAIL to eMail,
                            FULLNAME to fullname,
                            PASSWORD to password
                        )

                        db.collection(COLLECTION_PATH).document(fbUser.uid)
                            .set(user)
                            .addOnSuccessListener {
                                isSignUp.value = true
                                Log.d(SIGN_UP, SUCCESS)
                            }
                            .addOnFailureListener { e ->
                                isSignUp.value = false
                                Log.w(SIGN_UP, FAILURE, e)
                            }
                    }

                } else {
                    isSignUp.value = false
                    Log.w(SIGN_UP, FAILURE, task.exception)
                }
            }
        }
    }

    fun signOut() {
        coroutineScope.launch {
            auth.signOut()
        }

    }
}


//        fun getUserInfo() {
//            auth.currentUser?.let { user ->
//
//                val docRef = db.collection("users").document(user.uid)
//                docRef.get()
//                    .addOnSuccessListener { document ->
//                        document?.let {
//                            userInfo.value = FirebaseUserModel(
//                                user.email,
//                                document.get("fullname") as String,
//                                document.get("phonenumber") as String
//                            )
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        Log.d(ContentValues.TAG, "get failed with ", exception)
//                    }
//            }
//        }

