package com.example.hotels.domain.models

import java.io.Serializable

data class CardItems(
    val name: String = "",
    val image: String = "",
    val price: String = "",
    var id: String="",
    var checkout_quantity:String=""

) : Serializable
