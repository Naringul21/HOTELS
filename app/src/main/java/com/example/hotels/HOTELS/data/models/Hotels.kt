package com.example.hotels.HOTELS.data.models

import java.io.Serializable

data class Hotels(
    val name: String = "",
    val location: String = "",
    val image: String = "",
    val price: String = "",
    val meal: String = "",
    val room: String = "",
    val rating: Int = 1,
    val description: String = "",
    val lat: Double=0.0,
    val lng: Double=0.0

) : Serializable
