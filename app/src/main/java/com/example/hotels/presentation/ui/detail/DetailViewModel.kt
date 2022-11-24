package com.example.hotels.HOTELS.presentation.ui.detail_fragment

import androidx.databinding.ObservableMap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.presentation.adapter.FavoriteAdapter
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class DetailViewModel(): ViewModel() {
    private val repo=FirestoreRepositoryImpl()
    private var favoriteItem = ArrayList<FavoriteItem>()
    private lateinit var favoriteAdapter: FavoriteAdapter

    fun addFavoriteList(favoriteItem: FavoriteItem){
        repo.addToFavoriteItem(favoriteItem)
    }

    fun removeFromFavoriteList(favorite: FavoriteItem) {
        repo.removeItemFavorite(favorite)

    }

    fun addToCart(cartItem: CardItems){
        repo.addToCardItem(cartItem)
    }

}