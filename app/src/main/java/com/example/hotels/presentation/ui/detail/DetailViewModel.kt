package com.example.hotels.HOTELS.presentation.ui.detail_fragment

import androidx.databinding.ObservableMap
import androidx.lifecycle.ViewModel
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class DetailViewModel(): ViewModel() {
    private val repo=FirestoreRepositoryImpl()
    fun addFavoriteList(favoriteItem: FavoriteItem){
        repo.addToFavoriteItem(favoriteItem)
    }

    fun removeFromFavoriteList(){
        repo.removeItemFavorite()
    }

    fun addToCart(cartItem: CardItems){
        repo.addToCardItem(cartItem)
    }

}