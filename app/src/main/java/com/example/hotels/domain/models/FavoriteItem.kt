package com.example.hotels.domain.models

import java.io.Serializable

data class FavoriteItem(
    val name: String = "",
    val location: String = "",
    val image: String = "",
    val price: String = "",
    var id: String="",
    val userId: String="",
    var isFavorite: Boolean = false,
    val documentId: String=""
): Serializable
