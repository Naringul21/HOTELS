package com.example.hotels.HOTELS.utils

import com.example.hotels.HOTELS.data.models.Hotels

interface Navigator {
    abstract fun navigate(hotel: Hotels)
}