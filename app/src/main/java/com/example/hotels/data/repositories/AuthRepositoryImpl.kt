package com.example.hotels.HOTELS.domain.repositorys

import android.content.ContentValues
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotels.HOTELS.data.models.User
import com.example.hotels.HOTELS.utils.Constants.COLLECTION_PATH
import com.example.hotels.HOTELS.utils.Constants.E_MAIL
import com.example.hotels.HOTELS.utils.Constants.FAILURE
import com.example.hotels.HOTELS.utils.Constants.FULLNAME
import com.example.hotels.HOTELS.utils.Constants.ID
import com.example.hotels.HOTELS.utils.Constants.PASSWORD
import com.example.hotels.HOTELS.utils.Constants.SIGN_IN
import com.example.hotels.HOTELS.utils.Constants.SIGN_UP
import com.example.hotels.HOTELS.utils.Constants.SUCCESS
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.domain.repositories.AuthRepository
import com.example.hotels.presentation.ui.favorite.FavoriteFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepositoryImpl() : AuthRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    var isSignIn = MutableLiveData<Boolean>()
    var isSignUp = MutableLiveData<Boolean>()
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    override fun signIn(eMail: String, password: String) {

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

    override fun signUp(eMail: String, password: String, fullname: String) {

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
                            Log.w(SIGN_UP, "Error", e)
                        }
                }

            } else {
                isSignUp.value = false
                Log.w(SIGN_UP, FAILURE, task.exception)
            }
        }
    }

//    fun getUserInfo() : MutableLiveData<User> {
//        var userInfo = MutableLiveData<User>()
//        auth.currentUser?.let { user ->
//
//            val docRef = db.collection("users").document(user.uid)
//            docRef.get()
//                .addOnSuccessListener { document ->
//                    document?.let {
//                        userInfo.value = User(
//                            document.get("email") as String,
//                            document.get("fullname") as String
//                        )
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    Log.d(ContentValues.TAG, "get failed with ", exception)
//                }
//        }
//        return userInfo
//    }

//    fun getUser(): MutableLiveData<MutableList<User>> {
//        val mutableData = MutableLiveData<MutableList<User>>()
//        val userList = ArrayList<User>()
//        coroutineScope.launch {
//            db.collection("users")
//                .whereEqualTo("userId", FirestoreRepositoryImpl().getCurrentUserID())
//                .get()
//                .addOnSuccessListener {
//                    val userData: MutableList<User> = mutableListOf<User>()
//                    for (document: QueryDocumentSnapshot in it) {
//                        val docId = document.getString("documentId") ?: ""
//                        val fullname: String = document.getString("fullname") ?: ""
//                        val id: String = document.getString("id") ?: ""
//                        val email: String=document.getString("email") ?:""
//                        val user =
//                            User(fullName = fullname,
//                                user_id = id,
//                                documentId = docId
//                            )
//                        userData.add(user)
//                    }
//                    mutableData.value = userData
//
//        }}
//        return mutableData
//    }


    override fun signOut() {
        coroutineScope.launch {
            auth.signOut()
        }

    }


}


