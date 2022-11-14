package com.example.hotels.domain.models

data class FavoriteItem(
    val name: String = "",
    val location: String = "",
    val image: String = "",
    val price: String = "",
    val id: String="",
    var isFavorite: Boolean = false
)
