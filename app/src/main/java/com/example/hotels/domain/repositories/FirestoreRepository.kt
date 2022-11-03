package com.example.hotels.domain.repositories

import androidx.lifecycle.LiveData
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.ui.detail_fragment.DetailFragment
import com.example.hotels.domain.models.CardItems
import com.example.hotels.presentation.ui.CartFragment

interface FirestoreRepository {
    fun addToCardItem(fragment: DetailFragment, card: CardItems)

    fun removeFromCard(fragment: CartFragment, id:String, position:Int)

    fun getHotelData(): LiveData<MutableList<Hotels>>
}