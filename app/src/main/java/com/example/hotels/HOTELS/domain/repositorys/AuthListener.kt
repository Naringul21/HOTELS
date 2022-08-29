package com.example.hotels.HOTELS.domain.repositorys

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)

}