package com.example.hotels.presentation.ui.cart

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.CardItems

class CartViewModel :ViewModel() {
    private val repo = FirestoreRepositoryImpl()
    fun getDataCart() :LiveData<MutableList<CardItems>>  {
       return repo.getCartItems(CartFragment())
    }

    fun removeCartItem(cart: CardItems){
        repo.removeItemCart(cart)
    }
}