package com.example.hotels.domain.models

import com.google.gson.annotations.SerializedName

data class HotelResponse(
    val id: Int,
    @SerializedName("name")
    val hotelName: String?=null,
    @SerializedName("price")
    val hotelPrice: Int?=null,
    @SerializedName("image")
    val hotelImage: String?=null,
    @SerializedName("desc")
    val hotelDesc: String?=null,
    @SerializedName("info")
    val mealInfo: String?=null,
)
