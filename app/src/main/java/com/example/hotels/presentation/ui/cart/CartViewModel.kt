package com.example.hotels.presentation.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.CardItems

class CartViewModel :ViewModel() {
    private val repo = FirestoreRepositoryImpl()
    fun getDataCart() :LiveData<MutableList<CardItems>>  {
       return repo.getCartItems(CartFragment())
    }
}