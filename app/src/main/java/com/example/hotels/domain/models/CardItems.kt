package com.example.hotels.domain.models

import java.io.Serializable

data class CardItems(
    val name: String = "",
    val image: String = "",
    val price: String = "",
    val id: String = "",
    var checkout_quantity: String = "",
    val userId: String = "",

    ) : Serializable
