package com.example.hotels.domain.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.ui.detail_fragment.DetailFragment
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.presentation.ui.CartFragment

interface FirestoreRepository {
    fun addToCardItem(card: CardItems)

    fun removeItemCart(position:Int)

    fun getHotelData(): LiveData<MutableList<Hotels>>

    fun addToFavoriteItem()

    fun removeItemFavorite()

}