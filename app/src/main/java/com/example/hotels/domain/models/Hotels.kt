package com.example.hotels.HOTELS.data.models

import java.io.Serializable

data class Hotels(
    val name: String = "",
    val location: String = "",
    val image: String = "",
    val price: String = "",
    val meal: String = "",
    val room: String = "",
    val description: String = "",
    val lat: Double=0.0,
    val lng: Double=0.0,
    val rating: Long =0,
    val id: Int=0,

) : Serializable
