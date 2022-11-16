package com.example.hotels.domain.models

import java.io.Serializable

data class FavoriteItem(
    val name: String = "",
    val location: String = "",
    val image: String = "",
    val price: String = "",
    val id: String="",
    val isFavorite: Boolean = false,
    val userId: String=""
): Serializable
