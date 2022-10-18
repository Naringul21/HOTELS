package com.example.hotels.utils

import com.example.hotels.HOTELS.data.models.Hotels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object HotelUtils {
        var hotel: Hotels? = null

        fun getCurrentUser(){
            if(FirebaseAuth.getInstance().currentUser != null){
                FirebaseFirestore.getInstance().collection("Users")
                    .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                    .get().addOnCompleteListener {
                        hotel = it.result?.toObject(Hotels::class.java)
                    }
            }
        }
    }