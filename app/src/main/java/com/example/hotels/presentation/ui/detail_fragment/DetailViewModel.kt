package com.example.hotels.HOTELS.presentation.ui.detail_fragment

import androidx.databinding.ObservableMap
import androidx.lifecycle.ViewModel
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class DetailViewModel(): ViewModel() , OnMapReadyCallback {
    private val repo=FirestoreRepositoryImpl()
    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
    fun addFavoriteList(){
        repo.addToFavoriteItem()
    }

    fun removeFromFavoriteList(){
        repo.removeItemFavorite()
    }

}