package com.example.hotels.domain.repositories

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem

interface FirestoreRepository {
    fun addToCardItem(card: CardItems)

    fun removeItemCart(cart: CardItems)

    fun getHotelData(): LiveData<MutableList<Hotels>>

    fun addToFavoriteItem(favoriteItem: FavoriteItem)

    fun removeItemFavorite(favorite: FavoriteItem)

    fun getCartItems(fragment: Fragment): LiveData<MutableList<CardItems>>

}