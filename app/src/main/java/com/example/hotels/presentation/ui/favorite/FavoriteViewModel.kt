package com.example.hotels.presentation.ui.favorite

import android.app.Application
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.presentation.adapter.FavoriteAdapter
import dagger.hilt.android.internal.Contexts.getApplication

class FavoriteViewModel: ViewModel() {
    private val repo = FirestoreRepositoryImpl()
    private var favoriteItem = ArrayList<FavoriteItem>()
    private lateinit var favoriteAdapter: FavoriteAdapter

    fun getFavoriteItems(fragment: Fragment): LiveData<MutableList<FavoriteItem>> {
        return repo.getFavoriteItems(fragment)
    }

    fun removeFromFavorite(favorite: FavoriteItem) {
        repo.removeItemFavorite(favorite)

    }
}

