package com.example.hotels.domain.repositories

import androidx.lifecycle.LiveData
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem

interface FirestoreRepository {
    fun addToCardItem(card: CardItems)

    fun removeItemCart()

    fun getHotelData(): LiveData<MutableList<Hotels>>

    fun addToFavoriteItem(favoriteItem: FavoriteItem)

    fun removeItemFavorite()

}