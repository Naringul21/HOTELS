package com.example.hotels.data.repositorys

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotels.HOTELS.data.models.Hotels
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirestoreInstanceRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getHotelData(): LiveData<MutableList<Hotels>> {

        val mutableData = MutableLiveData<MutableList<Hotels>>()
        coroutineScope.launch {  }
        FirebaseFirestore.getInstance().collection("hotelList_seeAll_fragments").get()
            .addOnSuccessListener {
                val listData: MutableList<Hotels> = mutableListOf<Hotels>()
                for (document: QueryDocumentSnapshot in it) {
                    val image: String = document.getString("image") ?: ""
                    val location: String = document.getString("infoLocation") ?: ""
                    val name: String = document.getString("name") ?: ""
                    val price: String = document.getString("price") ?: ""
                    val room: String = document.getString("infoRoom") ?: ""
                    val meal: String = document.getString("infoMeal") ?: ""
                    val description: String = document.getString("description") ?: ""
                    val lat: Double = document.getDouble("lat") ?: 0.0
                    val lng: Double = document.getDouble("lng") ?: 0.0
                    val rating: Long = document.getLong("rating") ?: 0
                    val hotels =
                        Hotels(name,
                            location,
                            image,
                            price,
                            meal,
                            room,
                            description,
                            lat,
                            lng,
                            rating)
                    listData.add(hotels)
                }
                mutableData.value = listData
            }
        return mutableData
    }

}