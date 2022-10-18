package com.example.hotels.data.repositorys

import com.example.hotels.domain.models.HotelResponse
import retrofit2.Response
import retrofit2.http.GET

interface HotelApi {

    @GET("/api/fruit/all")
    suspend fun getHotelInfo() : Response<List<HotelResponse>>


}