package com.example.hotels.HOTELS.data.db

import java.io.Serializable

data class Hotels(
    val name:String="",
    val location: String="",
    val image: String="",
    val price: String="",
    val meal: String="",
    val room: String="",
    val rating: Int=1
):Serializable
