package com.example.hotels.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.CardItems
import com.google.firebase.firestore.FirebaseFirestore

class CartViewModel :ViewModel() {
    private val repo = FirestoreRepositoryImpl()
    fun getDataCart() :LiveData<MutableList<CardItems>>  {
       return repo.getCartItems(CartFragment())
    }
}