package com.example.hotels.HOTELS.domain.repositorys

interface FirebaseInstanceRepositoryInterface {
    fun createUserFirebase(email:String, password: String)
    fun loginUserFirebase(email:String, password: String)
}